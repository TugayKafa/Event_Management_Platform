package com.project.EventManagementPlatform.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException() {
    }

    public PlaceNotFoundException(Long id) {
        super(String.format("Place with id $d not found", id));
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }

    public PlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceNotFoundException(Throwable cause) {
        super(cause);
    }
}
