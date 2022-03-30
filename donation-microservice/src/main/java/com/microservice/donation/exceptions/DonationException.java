package com.microservice.donation.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class DonationException {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public DonationException(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public DonationException(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

}
