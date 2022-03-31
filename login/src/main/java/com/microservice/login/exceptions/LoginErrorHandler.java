package com.microservice.login.exceptions;


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
public class LoginErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    LoginResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        LoginResponse error = new LoginResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addErrorMsgToResponse(fieldError.getDefaultMessage(),ex);
        }
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    LoginResponse onConstraintValidationException(
            ConstraintViolationException e) {
        LoginResponse error = new LoginResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.addErrorMsgToResponse(violation.getMessage(),e);
        }
        return error;
    }
}