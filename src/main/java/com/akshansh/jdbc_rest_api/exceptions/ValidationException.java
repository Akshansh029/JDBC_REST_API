package com.akshansh.jdbc_rest_api.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg){
        super(msg);
    }
}
