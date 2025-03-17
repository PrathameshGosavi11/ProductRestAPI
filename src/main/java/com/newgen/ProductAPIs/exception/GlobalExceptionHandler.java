package com.newgen.ProductAPIs.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorDetails>  handleProductNotFoundException(ProductNotFound e)
    {
        System.err.println(e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage(),"Resources not found");
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductCategoryException.class)
    public  ResponseEntity<ErrorDetails> handleInvalidProductCategoryException(InvalidProductCategoryException e)
    {
        System.err.println(e);
        //log.error("invalid product category",e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(),"Invalid input");
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(InvalidArgumentException.class)
    public  ResponseEntity<ErrorDetails> handleInvalidArgumentException(InvalidArgumentException e)
    {
        System.err.println(e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"you pass Invalid Name");
        return  new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleInvalidArgumentException(IllegalArgumentException e)
    {
        System.err.println(e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(),"you pass invalid enum category");
        return  new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public  ResponseEntity<ErrorDetails> handleGenericException(Exception e)
    {
        System.err.println(e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong please check afte some time","server side Processing error");
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
