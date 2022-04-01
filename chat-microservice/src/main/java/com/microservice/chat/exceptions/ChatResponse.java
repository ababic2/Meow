package com.microservice.chat.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@ControllerAdvice
public class ChatResponse<T> {

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

    public static <T> ChatResponse<T> badRequest() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> ChatResponse<T> ok() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> ChatResponse<T> unauthorized() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.UNAUTHORIZED);
        return response;
    }

    public static <T> ChatResponse<T> validationException() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> ChatResponse<T> wrongCredentials() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> ChatResponse<T> accessDenied() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.ACCESS_DENIED);
        return response;
    }

    public static <T> ChatResponse<T> exception() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> ChatResponse<T> notFound() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> ChatResponse<T> duplicateEntity() {
        ChatResponse<T> response = new ChatResponse<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public ChatResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public ChatResponse setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Object getErrors() {
        return errors;
    }

    public ChatResponse setErrors(List<Object> errors) {
        this.errors = errors;
        return this;
    }

    public Object getMetadata() {
        return metadata;
    }

    public ChatResponse setMetadata(Object metadata) {
        this.metadata = metadata;
        return this;
    }

    public ChatResponse addErrorMsgToResponse(String errorMsg, Exception ex) {
        ChatError error = new ChatError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(new Date());
        errors.add(error);
        return this;
    }

}
