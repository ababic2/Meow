package com.microservice.donation.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@ControllerAdvice
public class DonationResponse<T> {

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

    public static <T> DonationResponse<T> badRequest() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> DonationResponse<T> ok() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> DonationResponse<T> unauthorized() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.UNAUTHORIZED);
        return response;
    }

    public static <T> DonationResponse<T> validationException() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.VALIDATION_EXCEPTION);
        return response;
    }

    public static <T> DonationResponse<T> wrongCredentials() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.WRONG_CREDENTIALS);
        return response;
    }

    public static <T> DonationResponse<T> accessDenied() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.ACCESS_DENIED);
        return response;
    }

    public static <T> DonationResponse<T> exception() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> DonationResponse<T> notFound() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> DonationResponse<T> duplicateEntity() {
        DonationResponse<T> response = new DonationResponse<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public DonationResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public DonationResponse setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Object getErrors() {
        return errors;
    }

    public DonationResponse setErrors(List<Object> errors) {
        this.errors = errors;
        return this;
    }

    public Object getMetadata() {
        return metadata;
    }

    public DonationResponse setMetadata(Object metadata) {
        this.metadata = metadata;
        return this;
    }

    public DonationResponse addErrorMsgToResponse(String errorMsg, Exception ex) {
        DonationError error = new DonationError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(new Date());
        errors.add(error);
        return this;
    }

}
