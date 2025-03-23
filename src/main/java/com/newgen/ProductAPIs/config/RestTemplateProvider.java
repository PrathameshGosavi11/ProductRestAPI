package com.newgen.ProductAPIs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestTemplateProvider {


    private final RestTemplate restTemplate=new RestTemplate();

    public  RestTemplate getRestTemplate()
    {
        return restTemplate;
    }
}
