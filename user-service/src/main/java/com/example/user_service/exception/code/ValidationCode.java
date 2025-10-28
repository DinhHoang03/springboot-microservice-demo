package com.example.user_service.exception.code;

import org.springframework.http.HttpStatus;

public enum ValidationCode {
    UNKNOWN_VALIDATOR(100, "Unknown Validator", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatus status;

    ValidationCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
