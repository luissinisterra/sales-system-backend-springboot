package com.api.sales_system.exception;

public class CurrentPasswordInvalidException extends RuntimeException {
    public CurrentPasswordInvalidException(String message) {
        super(message);
    }
}
