package com.newgen.ProductAPIs.controller;

import com.newgen.ProductAPIs.Service.ProductService;
import com.newgen.ProductAPIs.exception.InvalidProductCategoryException;
import com.newgen.ProductAPIs.exception.ProductNotFound;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import com.newgen.ProductAPIs.model.ProductSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {


    // object of ProductService //but not good Approch in a spring.//its hardcoded.
    //  private  ProductService productService=new ProductService();


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public  List<Product> getAllProduct(@RequestBody  ProductSearchCriteria productSearchCriteria)
    {
        System.out.println("product API controller called =>" + productSearchCriteria.getCategory());
        System.out.println("Lower price is :" +productSearchCriteria.getLowerPrice());
        System.out.println("Higher price is :" + productSearchCriteria.getHigherPrice());
        if (productSearchCriteria.getCategory() != null) { //if value present then enter only if  block
            Category catSearch = Category.valueOf(String.valueOf(productSearchCriteria.getCategory()));
            return productService.searchByCategory(catSearch);

        }
        if (productSearchCriteria.getLowerPrice() !=null && productSearchCriteria.getHigherPrice() != null)
            return productService.searchProdcutByRange(productSearchCriteria.getLowerPrice(), productSearchCriteria.getHigherPrice());

        if (productSearchCriteria.getName() != null) {
            return productService.searchByName(productSearchCriteria.getName());
        }
        return productService.getAllProduct(); //if not found category then return all product
    }

//    @GetMapping()
//    // @RequestMapping(method = RequestMethod.GET)
//    public List<Product> getALlProduct(@RequestParam(name = "category", required = false) String category,
//                                       @RequestParam(name = "name", required = false) String name,
//                                       @RequestParam(name = "lower-price", required = false) Double lowerPrice,
//                                       @RequestParam(name = "higher-price", required = false) Double higherPrice
//    ) {
//
//        System.out.println("product API controller called =>" + category);
//        System.out.println("Lower price is :" + lowerPrice);
//        System.out.println("Higher price is :" + higherPrice);
//        if (category != null) { //if value present then enter only if  block
//            Category catSearch = Category.valueOf(category);
//            return productService.searchByCategory(catSearch);
//
//        }
//        if (lowerPrice != null && higherPrice != null) {
//            return productService.searchProdcutByRange(lowerPrice, higherPrice);
//        }
//        if (name != null) {
//            return productService.searchByName(name);
//        }
//        return productService.getAllProduct(); //if not found category then return all product
//    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") Long productId) {

        System.out.println("here request is coming =>" + productId);

            Product product = productService.getProductById(productId);
            return new ResponseEntity(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long productId) {
        System.out.println("deletet request on controller-->" + productId);

            productService.deleteProduct(productId);
            return new ResponseEntity<>("product deleted successfully", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addProduct(@RequestBody Product product) //here all data get @Requestbody
    {
        System.out.println("Product Controller add method called ");

                productService.add(product);
            return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable(name = "id") Long productId) {
        System.out.println("Product Controller update method called ");
        product.setId(productId);

        productService.updateProduct(product);
        return new ResponseEntity<>("Product update successfully ", HttpStatus.OK);
    }
    //    @GetMapping("api/v1/products/search/{category}")
//    public @ResponseBody List<Product> searchProductByCategory(@PathVariable Category category) {
//        System.out.println("product API controller called ");
//        return productService.searchByCategory(category);
//
//    }

}





