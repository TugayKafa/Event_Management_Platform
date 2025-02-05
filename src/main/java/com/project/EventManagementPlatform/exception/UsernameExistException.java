package com.project.EventManagementPlatform.exception;

public class UsernameExistException extends RuntimeException {
    public UsernameExistException() {
        super("User with that username already exist!");
    }

    public UsernameExistException(String username) {
        super(String.format("User with username '$s' already exists!", username));
    }

    public UsernameExistException(String username, Throwable cause) {
        super(String.format("User with username '$s' already exists!", username), cause);
    }

    public UsernameExistException(Throwable cause) {
        super(cause);
    }
}
