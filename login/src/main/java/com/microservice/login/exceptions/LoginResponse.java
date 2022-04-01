package com.microservice.login.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@ControllerAdvice
public class LoginResponse<T> {

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
    private List<Object> errors = new ArrayList<>();
    private Object metadata;

    public static <T> LoginResponse<T> badRequest() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> LoginResponse<T> ok() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> LoginResponse<T> unauthorized() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.UNAUTHORIZED);
        return response;
    }

    public static <T> LoginResponse<T> validationException() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> LoginResponse<T> wrongCredentials() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> LoginResponse<T> accessDenied() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.ACCESS_DENIED);
        return response;
    }

    public static <T> LoginResponse<T> exception() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> LoginResponse<T> notFound() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> LoginResponse<T> duplicateEntity() {
        LoginResponse<T> response = new LoginResponse<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public LoginResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public LoginResponse setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Object getErrors() {
        return errors;
    }

    public LoginResponse setErrors(List<Object> errors) {
        this.errors = errors;
        return this;
    }

    public Object getMetadata() {
        return metadata;
    }

    public LoginResponse setMetadata(Object metadata) {
        this.metadata = metadata;
        return this;
    }

    public LoginResponse addErrorMsgToResponse(String errorMsg, Exception ex) {
        LoginError error = new LoginError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(new Date());
        errors.add(error);
        return this;
    }

}
