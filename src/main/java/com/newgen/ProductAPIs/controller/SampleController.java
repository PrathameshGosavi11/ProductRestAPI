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
        System.out.println("get post API CALLED ");
        return "POST";
    }

}
