package com.example.uks.exceptions;

public class OrganisationNotFound extends RuntimeException {
    public OrganisationNotFound(String message) {
        super(message);
    }
}
