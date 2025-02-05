package com.project.EventManagementPlatform.entity;

public enum Role {

    REGISTERED("ROLE_REGISTERED"),
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
