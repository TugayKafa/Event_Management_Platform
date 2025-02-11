package com.project.EventManagementPlatform.service;

import com.project.EventManagementPlatform.dto.UserDto;
import com.project.EventManagementPlatform.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto user);

    User updateUser(User user);

    User deleteUser(User user);

    User getUserByUsername(String username);

    List<User> getAllUsers();
}
