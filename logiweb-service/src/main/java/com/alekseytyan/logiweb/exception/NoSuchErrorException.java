package com.alekseytyan.logiweb.exception;

public class NoSuchErrorException extends RuntimeException {
    public NoSuchErrorException(String message) {
        super(message);
    }
}
