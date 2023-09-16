package com.uk.savechildrenuk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class acts as s global exception handler.
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value= UserNotFoundException.class)
    public ResponseEntity<ApiError> handleNoUserIdFoundException(){
        ApiError error=new ApiError(HttpStatus.NOT_FOUND.value(), "User Id Not Found");
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
