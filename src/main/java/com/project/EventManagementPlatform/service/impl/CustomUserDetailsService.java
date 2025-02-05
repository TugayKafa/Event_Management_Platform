package com.project.EventManagementPlatform.service.impl;

import com.project.EventManagementPlatform.entity.CustomUserDetails;
import com.project.EventManagementPlatform.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(userService.getUserByUsername(username));
    }
}
