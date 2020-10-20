package com.vlad.model;

public class AppException extends Exception {

    private int errorCode;
    private String error;

    public AppException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public AppException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AppException(String message, int errorCode, String error) {
        super(message);
        this.errorCode = errorCode;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
