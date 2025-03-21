package com.newgen.ProductAPIs.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.logging.Logger;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger=Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorDetails>  handleProductNotFoundException(ProductNotFound e)
    {
        //System.err.println(e);
        log.error("Product not found: {}", e.getMessage(), e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage(),"Resources not found");
        return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductCategoryException.class)
    public  ResponseEntity<ErrorDetails> handleInvalidProductCategoryException(InvalidProductCategoryException e)
    {
       // System.err.println(e);
        log.error("Invalid product category: {}", e.getMessage(), e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(),"Invalid input");
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(InvalidArgumentException.class)
    public  ResponseEntity<ErrorDetails> handleInvalidArgumentException(InvalidArgumentException e)
    {
       // System.err.println(e);
        log.error("Invalid Argument: {}", e.getMessage(), e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"you pass Invalid Name");
        return  new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleInvalidArgumentException(IllegalArgumentException e)
    {
        //System.err.println(e);
        log.error("Illegal argument: {}", e.getMessage(), e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(),"you pass invalid enum category");
        return  new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        final List<FieldError> fieldErrors= e.getBindingResult().getFieldErrors();
        for(FieldError fieldError: fieldErrors)
        {
            log.error("Invalid Argument {} : for {}",fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(),"you pass invalid enum category");
        return  new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler
    public  ResponseEntity<ErrorDetails> handleGenericException(Exception e)
    {
        // System.err.println(e);
        log.error("Generic Exception: {}", e.getMessage(), e);
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong please check afte some time","server side Processing error");
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
