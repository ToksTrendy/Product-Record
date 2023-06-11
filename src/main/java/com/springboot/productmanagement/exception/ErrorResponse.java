package com.springboot.productmanagement.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ErrorResponse {
    private Instant timestamp = Instant.now();
    private String message;
    private String description;
    private int errorCode;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ErrorResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorResponse setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }
    public static ErrorResponse buildErrorResponse(String description, HttpStatus status){
        return new ErrorResponse()
                .setDescription(description)
                .setErrorCode(status.value())
                .setMessage(status.getReasonPhrase());
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
