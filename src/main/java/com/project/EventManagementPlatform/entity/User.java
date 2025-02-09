package com.project.EventManagementPlatform.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

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

    @OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY)
    private Set<Event> organizedEvents;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private Set<Event> participatingEvents;
}

