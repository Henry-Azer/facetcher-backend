package com.henry.facetcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * @author Henry Azer
 * @since 23/03/2023
 */
@Configuration
public class AuditorAwareConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareUser();
    }
}
