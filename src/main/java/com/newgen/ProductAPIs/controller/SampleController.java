package com.newgen.ProductAPIs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping()
    public String sayHello()
    {
        return "Hello developers";
    }
}
