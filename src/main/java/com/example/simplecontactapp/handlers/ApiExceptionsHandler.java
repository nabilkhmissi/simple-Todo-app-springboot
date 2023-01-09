package com.example.simplecontactapp.handlers;

import com.example.simplecontactapp.exceptions.EntityNotFoundException;
import com.example.simplecontactapp.exceptions.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionsHandler {

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception){
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .code(exception.getErrorCode())
                .httpCode(badRequest.value())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(errorDto, badRequest);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception){
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .build();
        return new ResponseEntity<>(errorDto, notFound);
    }
}
