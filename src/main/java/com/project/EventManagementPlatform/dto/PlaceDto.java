package com.project.EventManagementPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    @NotBlank
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @Positive(message = "Max capacity must be positive")
    private int maxCapacity;

    @NotBlank
    @Size(min = 5, message = "Location name must be at least 5 characters")
    private String location;
}
