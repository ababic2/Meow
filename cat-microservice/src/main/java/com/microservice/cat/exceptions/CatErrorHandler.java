package com.microservice.cat.exceptions;


import com.microservice.cat.entity.Cat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class CatErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    CatResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        CatResponse error = new CatResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addErrorMsgToResponse(fieldError.getDefaultMessage(),null);
        }
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    CatResponse onConstraintValidationException(
            ConstraintViolationException e) {
        CatResponse error = new CatResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.addErrorMsgToResponse(violation.getMessage(),null);
        }
        return error;
    }
}