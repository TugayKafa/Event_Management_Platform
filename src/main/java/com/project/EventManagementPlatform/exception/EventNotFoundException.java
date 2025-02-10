package com.project.EventManagementPlatform.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Event does not exist!");
    }

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventNotFoundException(Throwable cause) {
        super(cause);
    }
}
