package com.newgen.ProductAPIs.controller;

import com.newgen.ProductAPIs.Service.ProductService;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1")

public class ProductController {

    // object of ProductService //but not good Approch in a spring.//its hardcoded.
    //  private  ProductService productService=new ProductService();


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    // @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Product> getALlProduct() {
        System.out.println("product API controller called ");
        return productService.getAllProduct();

    }


    @GetMapping("/products/{id}")
    public @ResponseBody Product getProductDetailById(@PathVariable(name = "id") Long productId) {
        System.out.println("here request is coming =>" + productId);
        return productService.getProductById(productId);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public @ResponseBody String deleteProduct(@PathVariable long id) {
        System.out.println("deletet request on controller");
        boolean status = productService.deleteProduct(id);
        if (status)
            return "product deleted successfully";
        else
            return "product not found   & deletion operation failed ";

    }

    @PostMapping("/addProduct")
    public @ResponseBody String addProduct(@RequestBody Product product) //here all data get @Requestbody
    {
        System.out.println("Product Controller add method called ");
        productService.add(product);
        return "Product addedd successfully";

    }

    @PutMapping("/updateProduct/{id}")
    public @ResponseBody String  updateProduct(@RequestBody Product product, @PathVariable(name="id") Long productId) {
        System.out.println("Product Controller update method called ");
        product.setId(productId);
        boolean status = productService.updateProduct(product);
        if (status) {
            return "Product update successfully ";
        }
         else {
            return "Product not uupdated   here ";
        }



    }
}

