package com.project.EventManagementPlatform.exception;

public class PlaceExistException extends RuntimeException {
    public PlaceExistException() {
        super("Place already exists in database!");
    }

    public PlaceExistException(String name, String location) {
        super(String.format("Place with name '$s' and location '$s' already exists!", name, location));
    }

    public PlaceExistException(String name, String location, Throwable cause) {
        super(String.format("Place with name '$s' and location '$s' already exists!", name, location), cause);
    }

    public PlaceExistException(Throwable cause) {
        super(cause);
    }
}
