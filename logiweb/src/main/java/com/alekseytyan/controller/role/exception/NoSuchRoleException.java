package com.alekseytyan.controller.role.exception;

public class NoSuchRoleException extends RuntimeException {
    public NoSuchRoleException(String message) {
        super(message);
    }
}
