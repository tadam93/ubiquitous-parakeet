package com.adamtrev.portal.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    private static final String REGION = "us-west-2";

    @Bean
    public AmazonDynamoDB getClient() {
        return AmazonDynamoDBClient.builder()
                .withRegion(REGION)
                .build();
    }

    @Bean
    public DynamoDBMapper getMapper(final AmazonDynamoDB client) {
        return new DynamoDBMapper(client);
    }
}
