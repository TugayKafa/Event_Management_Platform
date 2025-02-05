package com.project.EventManagementPlatform.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    public static final String ROLE_STUDENT = "ROLE_ANONYMOUS";
    public static final String ROLE_INSTRUCTOR = "ROLE_ORGANIZER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_UNREGISTERED = "ROLE_ATTENDEE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @NonNull
    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    //    public User(String username, String password, String firstName, String lastName, String email) {
    //        this.username = username;
    //        this.password = password;
    //        this.firstName = firstName;
    //        this.lastName = lastName;
    //        this.email = email;
    //    }
}

