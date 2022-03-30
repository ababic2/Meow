package com.microservice.chat.exceptions;
import java.util.Date;

public class ChatError {

    private Date timestamp;
    private String message;
    private String details;


    public Date getTimestamp() {
        return timestamp;
    }

    public ChatError setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ChatError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ChatError setDetails(String details) {
        this.details = details;
        return this;
    }

    public ChatError() {
    }
}
