package com.newgen.ProductAPIs.controller;

import com.newgen.ProductAPIs.Service.ProductService;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/products")
public class ProductController
{

    // object of ProductService //but not good Approch in a spring.//its hardcoded.
  //  private  ProductService productService=new ProductService();


    private  ProductService productService;

    @Autowired
    public  ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping()
   // @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Product> getALlProduct()
    {
        System.out.println("product API controller called ");
        return productService.getAllProduct();
    }
}
