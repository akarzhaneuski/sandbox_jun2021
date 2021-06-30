package com.exadel.sandbox.team5.util;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;

public class AmazonClientS3 {

    @Bean
    public static AmazonS3 createClient() {
        return AmazonS3ClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.EU_WEST_3)
                .build();
    }
}
