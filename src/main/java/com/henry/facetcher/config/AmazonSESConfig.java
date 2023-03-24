package com.henry.facetcher.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.henry.facetcher.service.ConfigValueService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.henry.facetcher.constants.FacetcherConstants.*;

/**
 * @author Henry Azer
 * @since 23/03/2023
 */
@Configuration
public class AmazonSESConfig {
    private final ConfigValueService configValueService;

    public AmazonSESConfig(ConfigValueService configValueService) {
        this.configValueService = configValueService;
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(configValueService.findConfigValueByConfigKey(ACCESS_KEY),
                        configValueService.findConfigValueByConfigKey(SECRET_KEY)))).withRegion(configValueService.findConfigValueByConfigKey(REGION_STATIC)).build();
    }
}
