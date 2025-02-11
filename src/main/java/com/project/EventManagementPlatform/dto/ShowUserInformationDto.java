package com.project.EventManagementPlatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShowUserInformationDto {
    private String username;

    private String firstName;

    private String lastName;

    private String email;
}
