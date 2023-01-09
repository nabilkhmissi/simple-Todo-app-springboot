package com.example.simplecontactapp.handlers;

import com.example.simplecontactapp.exceptions.ErrorCodes;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ErrorDto {
    private ErrorCodes code;
    private String message;
    private int httpCode;
    private List<String> errors;
}
