package com.ciandt.summit.bootcamp2022.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponseMessage implements Serializable {

    private Date timestamp;

    private String message;

    private String details;

    private int statusCode;

    public ExceptionResponseMessage(Date currentDate, String message, String details, int statusCod) {
        this.timestamp = currentDate;
        this.message = message;
        this.details = details;
        this.statusCode = statusCod;
    }

    public ExceptionResponseMessage() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
