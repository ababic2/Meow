package com.microservice.login.exceptions;


import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class LoginErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    LoginResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        LoginResponse error = new LoginResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addErrorMsgToResponse(fieldError.getDefaultMessage(),null);
        }
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    LoginResponse onConstraintValidationException(
            ConstraintViolationException e) {
        LoginResponse error = new LoginResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.addErrorMsgToResponse(violation.getMessage(),null);
        }
        return error;
    }
}