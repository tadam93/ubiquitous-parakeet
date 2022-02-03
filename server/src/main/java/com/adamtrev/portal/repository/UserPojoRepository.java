package com.adamtrev.portal.repository;

import com.adamtrev.portal.data.StaleDataException;
import com.adamtrev.portal.data.UserPojo;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserPojoRepository {
    private final DynamoDBMapper mapper;

    public UserPojo create(final UserPojo pojo) {
        try {
            // set to null because we're attempting to create a new entity
            pojo.setRecordVersion(null);
            mapper.save(pojo);
        } catch (final ConditionalCheckFailedException e) {
            throw new StaleDataException("Entry already exists");
        }

        return pojo;
    }

    public UserPojo get(final String firstName, final String lastName) {
        return mapper.load(UserPojo.class, firstName, lastName);
    }

    public UserPojo delete(final String firstName, final String lastName) {
        final UserPojo pojo = get(firstName, lastName);
        if (pojo != null) {
            mapper.delete(pojo);
        }

        return pojo;
    }
}
