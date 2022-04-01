package com.microservice.cat.exceptions;


import com.microservice.cat.entity.Cat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CatErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    CatResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        CatResponse error = new CatResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addErrorMsgToResponse(fieldError.getDefaultMessage(), ex);
        }
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    CatResponse onConstraintValidationException(
            ConstraintViolationException e) {
        CatResponse error = new CatResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.addErrorMsgToResponse(violation.getMessage(), e);
        }
        return error;
    }
}