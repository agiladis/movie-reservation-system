package com.example.moviereservationsystem.exception;

public class UserErrorException extends RuntimeException {

    public UserErrorException(String message) {
        super(message);
    }
}
