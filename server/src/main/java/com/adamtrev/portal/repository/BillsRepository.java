package com.adamtrev.portal.repository;

import com.adamtrev.portal.data.BillsPojo;
import com.adamtrev.portal.data.StaleDataException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BillsRepository {
    private final DynamoDBMapper mapper;

    public BillsPojo createBill(final BillsPojo pojo) {
        try {
            mapper.save(pojo);
        } catch (final ConditionalCheckFailedException e) {
            throw new StaleDataException("Entry already exists");
        }

        return pojo;
    }

    public List<BillsPojo> getBills() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(BillsPojo.class, scanExpression);
    }

    public List<BillsPojo> getBillsForCompany(final String companyName) {
        final Map<String, AttributeValue> expressionAttributes = new HashMap<>();
        expressionAttributes.put(":pk", new AttributeValue().withS(companyName));

        final DynamoDBQueryExpression<BillsPojo> queryExpression = new DynamoDBQueryExpression<BillsPojo>()
                .withKeyConditionExpression("pk = :pk")
                .withExpressionAttributeValues(expressionAttributes);

        return mapper.query(BillsPojo.class, queryExpression);
    }

    public BillsPojo getBill(final String companyName, final DateTime payableDate) {
        return mapper.load(BillsPojo.class, companyName, payableDate);
    }

    public BillsPojo deleteBill(final String companyName, final DateTime payableDate) {
        final BillsPojo pojo = getBill(companyName, payableDate);
        if (pojo != null) {
            mapper.delete(pojo);
        }

        return pojo;
    }
}
