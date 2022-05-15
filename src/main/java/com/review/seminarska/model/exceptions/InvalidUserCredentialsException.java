package com.review.seminarska.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException() {
        super("Invalid user credentials exception");
    }
}
