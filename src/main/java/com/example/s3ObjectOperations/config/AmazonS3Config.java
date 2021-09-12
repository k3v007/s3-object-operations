package com.example.s3ObjectOperations.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vivek Kumar Sinha
 */
@Configuration
public class AmazonS3Config {

    @Value("${amazon.s3.region}")
    private String amazonS3Region;

    @Bean
    public AmazonS3 buildAmazonS3Config() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(amazonS3Region)
                .build();
    }
}
