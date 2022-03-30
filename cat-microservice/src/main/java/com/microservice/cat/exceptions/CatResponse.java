package com.microservice.cat.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CatResponse <T> {

    public enum Status {
        OK,
        BAD_REQUEST,
        UNAUTHORIZED,
        VALIDATION_EXCEPTION,
        EXCEPTION,
        WRONG_CREDENTIALS,
        ACCESS_DENIED,
        NOT_FOUND,
        DUPLICATE_ENTITY
    }

    private Status status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> CatResponse<T> badRequest() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> CatResponse<T> ok() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> CatResponse<T> unauthorized() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.UNAUTHORIZED);
        return response;
    }

    public static <T> CatResponse<T> validationException() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> CatResponse<T> wrongCredentials() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> CatResponse<T> accessDenied() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.ACCESS_DENIED);
        return response;
    }

    public static <T> CatResponse<T> exception() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> CatResponse<T> notFound() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> CatResponse<T> duplicateEntity() {
        CatResponse<T> response = new CatResponse<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public CatResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public CatResponse setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Object getErrors() {
        return errors;
    }

    public CatResponse setErrors(Object errors) {
        this.errors = errors;
        return this;
    }

    public Object getMetadata() {
        return metadata;
    }

    public CatResponse setMetadata(Object metadata) {
        this.metadata = metadata;
        return this;
    }

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        System.out.printf("\n\n\n\n Adding \n\n\n\n");
        CatError error = new CatError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(new Date());
        setErrors(error);
    }

}
