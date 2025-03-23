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

    @GetMapping("/posts")
    public String getPost()
    {
        RestTemplate restTemplate=new RestTemplate();
       String response= restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",String.class);
        log.info("get post API CALLED :{}" ,response);
        return response;
    }

}
