package com.newgen.ProductAPIs.controller;

import com.newgen.ProductAPIs.Service.IProductService;
import com.newgen.ProductAPIs.exception.ErrorDetails;
import com.newgen.ProductAPIs.exception.InvalidProductCategoryException;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    // object of ProductService //but not good Approch in a spring.//its hardcoded.
    //  private  ProductService productService=new ProductService();
    private  IProductService productService;

    @Autowired

    public ProductController(IProductService productService) {
        System.out.println("Product Controller construtor is called with the service :"+productService);
        this.productService = productService;

    }



    @GetMapping()
    // @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProduct(@RequestParam(name = "category",required = false) String category,
                                       @RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "lower-price", required = false) Double lowerPrice,
                                       @RequestParam(name = "higher-price", required = false) Double higherPrice
    ) {

        System.out.println("product API controller called =>" + category);
        System.out.println("Lower price is :" + lowerPrice);
        System.out.println("Higher price is :" + higherPrice);
        if (category != null) { //if value present then enter only if  block
            Category catSearch = Category.valueOf(category);
            return productService.searchByCategory(catSearch);

        }
        if (lowerPrice != null && higherPrice != null) {
            return productService.searchProdcutByRange(lowerPrice, higherPrice);
        }
        if (name != null) {
            return productService.searchByName(name);
        }
        return productService.getAllProduct(); //if not found category then return all product
    }


    @ApiResponse(description = "get Product by given Product Identifier ",responseCode = "200", content = @Content(mediaType = "application/JSON",schema = @Schema(implementation = Product.class)))
    @ApiResponse(description = "Product not Found",responseCode = "404",content = @Content(schema = @Schema(implementation=ErrorDetails.class)))
    @ApiResponse(description = "Internal server errorr",responseCode = "500",content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrive the Product by Id",
            description = "Get Product Object Specifying it's Product Identifier",
            tags = {"Products"}
    )
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
        System.out.println("Product Controller addProduct method called ");

                productService.add(product);
            return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable(name = "id")
    Long productId) {
        System.out.println("Product Controller update method called ");
        product.setId(productId);

        productService.updateProduct(product);
        return new ResponseEntity<>("Product update successfully ", HttpStatus.OK);
    }
    //    @GetMapping("api/v1/products/search/{category}")//    public @ResponseBody List<Product> searchProductByCategory(@PathVariable Category category) {
//        System.out.println("product API controller called ");
//        return productService.searchByCategory(category);
//
//    }

}





