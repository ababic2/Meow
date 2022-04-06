package com.microservice.catmisc.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@ControllerAdvice
public class CatMiscResponse<T> {

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

    public static <T> CatMiscResponse<T> badRequest() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> CatMiscResponse<T> ok() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> CatMiscResponse<T> unauthorized() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.UNAUTHORIZED);
        return response;
    }

    public static <T> CatMiscResponse<T> validationException() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> CatMiscResponse<T> wrongCredentials() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> CatMiscResponse<T> accessDenied() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.ACCESS_DENIED);
        return response;
    }

    public static <T> CatMiscResponse<T> exception() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> CatMiscResponse<T> notFound() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> CatMiscResponse<T> duplicateEntity() {
        CatMiscResponse<T> response = new CatMiscResponse<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public CatMiscResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public CatMiscResponse setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Object getErrors() {
        return errors;
    }

    public CatMiscResponse setErrors(List<Object> errors) {
        this.errors = errors;
        return this;
    }

    public Object getMetadata() {
        return metadata;
    }

    public CatMiscResponse setMetadata(Object metadata) {
        this.metadata = metadata;
        return this;
    }

    public CatMiscResponse addErrorMsgToResponse(String errorMsg, Exception ex) {
        CatMiscError error = new CatMiscError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(new Date());
        errors.add(error);
        return this;
    }

}
