package com.microservice.donation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class DonationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    DonationResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        DonationResponse error = new DonationResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addErrorMsgToResponse(fieldError.getDefaultMessage(),ex);
        }
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    DonationResponse onConstraintValidationException(
            ConstraintViolationException e) {
        DonationResponse error = new DonationResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.addErrorMsgToResponse(violation.getMessage(),e);
        }
        return error;
    }
}