package com.apsolutions.exception;

public class CsException extends RuntimeException {
    private String message;

    public CsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
