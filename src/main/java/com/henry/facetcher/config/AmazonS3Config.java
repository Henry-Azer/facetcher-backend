package com.henry.facetcher.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.henry.facetcher.service.ConfigValueService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.henry.facetcher.constants.FacetcherConstants.*;

/**
 * @author Henry Azer
 * @since 07/03/2023
 */
@Configuration
public class AmazonS3Config {
    private final ConfigValueService configValueService;

    public AmazonS3Config(ConfigValueService configValueService) {
        this.configValueService = configValueService;
    }

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(configValueService.findConfigValueByConfigKey(ACCESS_KEY), configValueService.findConfigValueByConfigKey(SECRET_KEY));
        return AmazonS3ClientBuilder.standard().withRegion(configValueService.findConfigValueByConfigKey(REGION_STATIC)).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }
}
