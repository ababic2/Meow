package com.microservice.cat.exceptions;
import java.util.Date;

public class CatError {

    private Date timestamp;
    private String message;
    private String details;


    public Date getTimestamp() {
        return timestamp;
    }

    public CatError setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CatError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public CatError setDetails(String details) {
        this.details = details;
        return this;
    }

    public CatError() {
    }
}
