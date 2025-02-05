package com.project.EventManagementPlatform.entity;

public enum Role {

    ATTENDEE("ROLE_ATTENDEE"),
    ORGANIZER("ROLE_ORGANIZER"),
    ADMIN("ROLE_ADMIN"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
