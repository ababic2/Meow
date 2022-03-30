package com.microservice.chat.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
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
    private Object errors;
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

    public ChatResponse setErrors(Object errors) {
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

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        System.out.printf("\n\n\n\n Adding \n\n\n\n");
        ChatError error = new ChatError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(new Date());
        setErrors(error);
    }

}
