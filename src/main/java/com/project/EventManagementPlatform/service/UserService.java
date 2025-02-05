package com.project.EventManagementPlatform.service;

import com.project.EventManagementPlatform.dto.UserDto;
import com.project.EventManagementPlatform.entity.Role;
import com.project.EventManagementPlatform.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto user);

    User updateUser(UserDto user);

    User deleteUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    List<User> getAllUsersByRole(Role role);
}
