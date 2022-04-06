package com.microservice.catmisc.exceptions;
import java.util.Date;

public class CatMiscError {

    private Date timestamp;
    private String message;
    private String details;


    public Date getTimestamp() {
        return timestamp;
    }

    public CatMiscError setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CatMiscError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public CatMiscError setDetails(String details) {
        this.details = details;
        return this;
    }

    public CatMiscError() {
    }
}
