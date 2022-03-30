package com.microservice.chat.exceptions;


import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class ChatErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ChatResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ChatResponse error = new ChatResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addErrorMsgToResponse(fieldError.getDefaultMessage(),null);
        }
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ChatResponse onConstraintValidationException(
            ConstraintViolationException e) {
        ChatResponse error = new ChatResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.addErrorMsgToResponse(violation.getMessage(),null);
        }
        return error;
    }
}