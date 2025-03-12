package com.newgen.ProductAPIs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String>  handleProductNotFoundException(ProductNotFound e)
    {
        System.err.println(e);
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductCategoryException.class)
    public  ResponseEntity<String> handleInvalidProductCategoryException(InvalidProductCategoryException e)
    {
        System.err.println(e);
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(InvalidArgumentException.class)
    public  ResponseEntity<String> handleInvalidArgumentException(InvalidArgumentException e)
    {
        System.err.println(e);
        return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public  ResponseEntity<String> handleGenericException(Exception e)
    {
        System.err.println(e);
        return  new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
