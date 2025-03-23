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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/products")
@Tag(name = "Product API",description = "Product related Operation")
@Slf4j
public class ProductController {


    //loger
    //private static final Logger log =  Logger.getLogger(ProductController.class.getName());

    private  IProductService productService;

    @Autowired

    public ProductController(IProductService productService) {
        log.info("Product Controller construtor is called with the service :"+productService);
        this.productService = productService;

    }

    //2025-03-21T16:14:35.607+05:30  INFO 15340 ---
    // [ProductAPIs] [           main] c.n.P.controller.ProductController
    // : Product Controller construtor is called with the service :com.newgen.ProductAPIs.Service.InMemoryProductService@3314f179



    @ApiResponses(
            value = {
                    @ApiResponse (
                            description = "Return all Product here:",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Something wrong on server side",
                            responseCode = "500",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Invalid Product Category",
                            responseCode = "400",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorDetails.class)
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

        log.info("Get All Prodcut controller called");
        log.info("product API controller called -> {} and name {}" , category,name);
        log.info("Lower price is :" + lowerPrice);
        log.info("Higher price is :" + higherPrice);
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

/*2025-03-21T16:40:26.508+05:30  INFO 14068 --- [ProductAPIs] [nio-8081-exec-4] c.n.P.controller.ProductController       : product API controller called =>null
            2025-03-21T16:40:26.508+05:30  INFO 14068 --- [ProductAPIs] [nio-8081-exec-4] c.n.P.controller.ProductController       : Lower price is :null
            2025-03-21T16:40:26.508+05:30  INFO 14068 --- [ProductAPIs] [nio-8081-exec-4] c.n.P.controller.ProductController       : Higher price is :null*/

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
    public ResponseEntity<?> getProductById(@Parameter(name="id",description = "Product Id",required = true)@PathVariable(name = "id") Long productId)
    {

        log.info("here request is coming =>" + productId);

            Product product = productService.getProductById(productId);
            //call to review service to get the review's for the given product and send it to client.
            //yaha se API ko call karna hain.
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
        log.info("delete request on controller-->" + productId);

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
    public ResponseEntity<String> addProduct(@RequestBody  Product product) //here all data get @Requestbody
    {
        log.info("Product Controller addProduct method called ");

                productService.add(product);
            return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @ApiResponses(
           value = {

                   @ApiResponse(
                           description = "Update the Product successfully",
                           responseCode = "200"
                   )
                   ,@ApiResponse(
                   description = "please provide correct category here ",
                           responseCode ="400",
                           content = @Content(
                                   schema = @Schema(implementation = ErrorDetails.class)
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
                           description = "Internal server error",
                           responseCode = "500",
                           content = @Content(
                                   schema = @Schema(implementation = ErrorDetails.class)
                           )
                   )
           }
    )
    @Operation(
            summary = "update the  Product ",
            description = "This Method modify Existing Product",
            operationId = "updateProduct"
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable(name = "id")
    Long productId) {

       log.info("Product Controller update method called ");
        product.setId(productId);

        productService.updateProduct(product);
        return new ResponseEntity<>("Product update successfully ", HttpStatus.OK);
    }


}





