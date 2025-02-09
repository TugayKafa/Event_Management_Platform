package com.project.EventManagementPlatform.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Place name cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @Positive(message = "Max capacity must be positive")
    private int maxCapacity;

    @NotEmpty(message = "Location cannot be empty")
    @Size(min = 5, message = "Location name must be at least 5 characters")
    private String location;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Event> events;

    public Place(String name, int maxCapacity, String location) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.location = location;
    }
}
