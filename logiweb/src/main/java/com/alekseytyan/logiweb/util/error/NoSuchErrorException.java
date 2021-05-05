package com.alekseytyan.logiweb.util.error;

public class NoSuchErrorException extends RuntimeException {
    public NoSuchErrorException(String message) {
        super(message);
    }
}
