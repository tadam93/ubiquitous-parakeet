package com.adamtrev.portal.repository;

import com.adamtrev.portal.datamodel.TestPojo;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class TestPojoRepository {
    private final DynamoDBMapper mapper;

    public TestPojo create(final TestPojo pojo) {
        // set to null because we're attempting to create a new entity
        pojo.setRecordVersion(null);
        mapper.save(pojo);

        // read and return the saved object so we have access to any metadata/updated record version on follow up
        // calls
        return mapper.load(pojo);
    }

    public TestPojo get(final String firstName, final String lastName) {
        return mapper.load(TestPojo.class, firstName, lastName);
    }
}
