package com.alekseytyan.util.error;

public class NoSuchErrorException extends RuntimeException {
    public NoSuchErrorException(String message) {
        super(message);
    }
}
