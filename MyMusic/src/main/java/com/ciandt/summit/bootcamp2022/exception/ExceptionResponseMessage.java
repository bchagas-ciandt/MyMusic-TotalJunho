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

    public static ExceptionResponseMessageBuilder builder() {
        return new ExceptionResponseMessageBuilder();
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

    public static class ExceptionResponseMessageBuilder {
        private Date timestamp;
        private String message;
        private String details;
        private int statusCode;

        ExceptionResponseMessageBuilder() {
        }

        public ExceptionResponseMessageBuilder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionResponseMessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ExceptionResponseMessageBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ExceptionResponseMessageBuilder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ExceptionResponseMessage build() {
            return new ExceptionResponseMessage(timestamp, message, details, statusCode);
        }

        public String toString() {
            return "ExceptionResponseMessage.ExceptionResponseMessageBuilder(timestamp=" + this.timestamp + ", message=" + this.message + ", details=" + this.details + ", statusCode=" + this.statusCode + ")";
        }
    }
}
