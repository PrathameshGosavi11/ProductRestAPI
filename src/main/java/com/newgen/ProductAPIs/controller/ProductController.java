package com.newgen.ProductAPIs.controller;

import com.newgen.ProductAPIs.Service.ProductService;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    // object of ProductService //but not good Approch in a spring.//its hardcoded.
    //  private  ProductService productService=new ProductService();


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    // @RequestMapping(method = RequestMethod.GET)
    public  List<Product> getALlProduct(@RequestParam(name="category",required = false) String category,
                                                     @RequestParam(name="name",required = false) String name,
                                                     @RequestParam(name="lower-price",required = false) Double lowerPrice,
                                                     @RequestParam(name="higher-price",required = false) Double higherPrice
                                                     ) {

        System.out.println("product API controller called =>"+category);
        System.out.println("Lower price is :"+lowerPrice);
        System.out.println("Higher price is :"+higherPrice);
        if(category !=null ) { //if value present then enter only if  block
            Category catSearch =Category.valueOf(category);
            return productService.searchByCategory(catSearch);

        }
        if(lowerPrice != null && higherPrice !=null)
        {
            return  productService.searchProdcutByRange(lowerPrice,higherPrice);
        }
        if(name!=null)
        {
            return  productService.searchByName(name);
        }
        return productService.getAllProduct(); //if not found category then return all product


    }


    @GetMapping("/{id}")
    public  Product getProductDetailById(@PathVariable(name = "id") Long productId) {
        System.out.println("here request is coming =>" + productId);
        return productService.getProductById(productId);
    }

    @DeleteMapping("/{id}")
    public  String deleteProduct(@PathVariable long id) {
        System.out.println("deletet request on controller");
        boolean status = productService.deleteProduct(id);
        if (status)
            return "product deleted successfully";
        else
            return "product not found   & deletion operation failed ";

    }

    @PostMapping()
    public  String addProduct(@RequestBody Product product) //here all data get @Requestbody
    {
        System.out.println("Product Controller add method called ");
        productService.add(product);
        return "Product addedd successfully";

    }

    @PutMapping("/{id}")
    public  String  updateProduct(@RequestBody Product product, @PathVariable(name="id") Long productId) {
        System.out.println("Product Controller update method called ");
        product.setId(productId);
        boolean status = productService.updateProduct(product);
        if (status) {
            return "Product update successfully ";
        }
         else {
            return "Product not found so update failed  here ";
        }
    }


//    @GetMapping("api/v1/products/search/{category}")
//    public @ResponseBody List<Product> searchProductByCategory(@PathVariable Category category) {
//        System.out.println("product API controller called ");
//        return productService.searchByCategory(category);
//
//    }
}

