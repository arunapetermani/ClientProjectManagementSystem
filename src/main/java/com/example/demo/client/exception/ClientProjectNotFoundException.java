package com.example.demo.client.exception;

public class ClientProjectNotFoundException extends RuntimeException{
    private int errorCode;
    private String errorMessage;

    public ClientProjectNotFoundException() {
    }

    public ClientProjectNotFoundException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
