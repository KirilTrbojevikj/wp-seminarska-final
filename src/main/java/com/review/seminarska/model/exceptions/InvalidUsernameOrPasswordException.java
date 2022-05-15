package com.review.seminarska.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{

    public InvalidUsernameOrPasswordException() {
        super("Invalid username and password credentials exception");
    }
}
