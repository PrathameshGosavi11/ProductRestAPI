package com.newgen.ProductAPIs;

import com.newgen.ProductAPIs.Service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;

@SpringBootApplication
public class ProductApIsApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(ProductApIsApplication.class, args);


//		ProductService service= applicationContext.getBean(ProductService.class);
//
//		service.getAllProduct().forEach(System.out::println);


    }


}
