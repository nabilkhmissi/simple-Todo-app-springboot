package com.example.simplecontactapp.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class InvalidEntityException extends RuntimeException{
    private List<String> errors;
    private ErrorCodes errorCode;

    public InvalidEntityException(String message,
                                  ErrorCodes errorCode,
                                  List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }
    public InvalidEntityException(String message,
                                  ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
