package com.newgen.ProductAPIs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter //used for send the response
public class ErrorDetails
{
    private int status;
    private  String message;
    private  String name;


}
