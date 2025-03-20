package com.newgen.ProductAPIs.controller;

import com.newgen.ProductAPIs.Service.IProductService;
import com.newgen.ProductAPIs.exception.ErrorDetails;
import com.newgen.ProductAPIs.exception.InvalidProductCategoryException;
import com.newgen.ProductAPIs.model.Category;
import com.newgen.ProductAPIs.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Tag(name = "Product API",description = "Product related Operation")
public class ProductController {

    // object of ProductService //but not good Approch in a spring.//its hardcoded.
    //  private  ProductService productService=new ProductService();
    private  IProductService productService;

    @Autowired

    public ProductController(IProductService productService) {
        System.out.println("Product Controller construtor is called with the service :"+productService);
        this.productService = productService;

    }



    @ApiResponses(
            value = {
                    @ApiResponse (
                            description = "Return all Product here:",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    )
            }
    )
    @Operation(
            summary = "Retrive all Product",
            description = "Return all Product here",
            operationId = "Get All Product"
    )
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



    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Get Product by given Product Identifier",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",  // Corrected "application/JSON" to "application/json"
                                    schema = @Schema(implementation = Product.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Product not Found",
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )
            }
    )
    @Operation(
            summary = "Retrive the Product by Id",
            description = "Get Product Object Specifying it's Product Identifier",
            operationId = "get Product By Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") Long productId) {

        System.out.println("here request is coming =>" + productId);

            Product product = productService.getProductById(productId);
            return new ResponseEntity(product, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Product by ID",
            description = "Deletes a product using its ID",
            operationId = "deleteProductById",
            parameters = {
                    @Parameter(name = "id", description = "Product ID", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200",description = "Product delete successfully"),
                    @ApiResponse(responseCode = "404",description = "Product Not Found"),
                    @ApiResponse(responseCode = "500",description = "Internal server error"),
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long productId) {
        System.out.println("delete request on controller-->" + productId);

            productService.deleteProduct(productId);
            return new ResponseEntity<>("product deleted successfully", HttpStatus.OK);
    }


    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Add new Product",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid category passed",
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    )

            }
    )
    @Operation(
            summary = "Add Product ",
            description = "This Method Add New Product",
            operationId = "addProduct"
    )
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


}





