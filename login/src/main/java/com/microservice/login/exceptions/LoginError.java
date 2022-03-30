package com.microservice.login.exceptions;
import java.util.Date;

public class LoginError {

    private Date timestamp;
    private String message;
    private String details;


    public Date getTimestamp() {
        return timestamp;
    }

    public LoginError setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public LoginError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public LoginError setDetails(String details) {
        this.details = details;
        return this;
    }

    public LoginError() {
    }
}
