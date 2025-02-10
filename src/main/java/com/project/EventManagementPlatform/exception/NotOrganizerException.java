package com.project.EventManagementPlatform.exception;

public class NotOrganizerException extends RuntimeException {
    public NotOrganizerException() {
        super();
    }

    public NotOrganizerException(String message) {
        super(message);
    }

    public NotOrganizerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotOrganizerException(Throwable cause) {
        super(cause);
    }
}
