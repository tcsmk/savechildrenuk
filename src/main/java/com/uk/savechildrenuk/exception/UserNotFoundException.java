package com.uk.savechildrenuk.exception;

/**
 * This class represents user defined exception.
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
