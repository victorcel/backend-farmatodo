package com.farmatodo.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionHandler {

    public ResponseEntity<Object> toResponse(Exception exception, HttpStatus status) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setType("ERROR");
        errorMessage.setCode(status.toString());
        errorMessage.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, status);
    }
}
