package com.project.EventManagementPlatform.exception;

public class EmailExistException extends RuntimeException {
    public EmailExistException() {
        super("User with that email already exists!");
    }

    public EmailExistException(String email) {
        super(String.format("User with email '%s' already exists!", email));
    }

    public EmailExistException(String email, Throwable cause) {
        super(String.format("User with email '%s' already exists!", email), cause);
    }

    public EmailExistException(Throwable cause) {
        super(cause);
    }
}
