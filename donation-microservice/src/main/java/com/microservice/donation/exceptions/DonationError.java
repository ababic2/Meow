package com.microservice.donation.exceptions;
import java.util.Date;

public class DonationError {

    private Date timestamp;
    private String message;
    private String details;


    public Date getTimestamp() {
        return timestamp;
    }

    public DonationError setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DonationError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public DonationError setDetails(String details) {
        this.details = details;
        return this;
    }

    public DonationError() {
    }
}
