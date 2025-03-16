package com.newgen.ProductAPIs;

import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import com.newgen.ProductAPIs.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProductApIsApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(ProductApIsApplication.class, args);


        ProductRepository productRepository=applicationContext.getBean(ProductRepository.class);

        productRepository.save(new Product("Kurti", Category.CLOTHES,540.90));

        System.out.println("product add successfully");

    }


}
