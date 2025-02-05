package com.project.EventManagementPlatform.config;

import com.project.EventManagementPlatform.service.UserService;
import com.project.EventManagementPlatform.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailsServiceConfig {
    private final UserService userService;

    public UserDetailsServiceConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userService);
    }
}