package com.exadel.sandbox.team5.util;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Lazy
@Configuration
public class AWSConfiguration {
    @Value("${app.region}")
    private String region;

    private final String REGION2 = "us-east-2";

    @Bean
    public AmazonS3 getClient() {
        return AmazonS3ClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.fromName(region))
                .build();
    }

    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.fromName(REGION2))
                .build();
    }

    @Bean
    public AmazonSQS sqsClient() {
        return AmazonSQSClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.fromName(region))
                .build();
    }
}
