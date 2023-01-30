package com.henry.facetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FacetcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacetcherApplication.class, args);
    }

}
