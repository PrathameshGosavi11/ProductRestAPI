package com.newgen.ProductAPIs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplateProvider restTemplateProvider()
    {
        return  new RestTemplateProvider();
    }
}
