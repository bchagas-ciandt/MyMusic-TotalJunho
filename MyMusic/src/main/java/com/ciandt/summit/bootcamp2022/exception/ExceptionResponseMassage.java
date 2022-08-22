package com.ciandt.summit.bootcamp2022.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponseMassage implements Serializable {

    private Date timestamp;

    private String message;

    private String details;

    public ExceptionResponseMassage(Date currentDate, String message, String details) {
        this.timestamp = currentDate;
        this.message = message;
        this.details = details;
    }

    public ExceptionResponseMassage() {
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
}
