package com.newgen.ProductAPIs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class SampleController {

    @GetMapping()
    public String sayHello()
    {
        return "Hello developers";
    }

    @GetMapping("/post")
    public String getPost()
    {
        RestTemplate restTemplate=new RestTemplate();
        String response= restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",String.class);

        //System.out.println("API response is :"+response);
        log.info("api response is {}  ",response);
        return "Hello your POST";
    }
}
