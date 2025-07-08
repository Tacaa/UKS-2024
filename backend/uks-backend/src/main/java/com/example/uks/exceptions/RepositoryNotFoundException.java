package com.example.uks.exceptions;


public class RepositoryNotFoundException extends RuntimeException {

    public RepositoryNotFoundException(String message) {
        super(message);
    }
}
