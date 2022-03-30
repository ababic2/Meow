package com.microservice.donation.exceptions;


import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class DonationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    DonationResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        DonationResponse error = new DonationResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addErrorMsgToResponse(fieldError.getDefaultMessage(),null);
        }
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    DonationResponse onConstraintValidationException(
            ConstraintViolationException e) {
        DonationResponse error = new DonationResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.addErrorMsgToResponse(violation.getMessage(),null);
        }
        return error;
    }
}