package com.project.EventManagementPlatform.service.impl;

import com.project.EventManagementPlatform.dto.UserDto;
import com.project.EventManagementPlatform.entity.Event;
import com.project.EventManagementPlatform.entity.Role;
import com.project.EventManagementPlatform.entity.User;
import com.project.EventManagementPlatform.exception.EmailExistException;
import com.project.EventManagementPlatform.exception.UserNotFoundException;
import com.project.EventManagementPlatform.exception.UsernameExistException;
import com.project.EventManagementPlatform.mapper.EntityMapper;
import com.project.EventManagementPlatform.repository.UserRepository;
import com.project.EventManagementPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UsernameExistException(userDto.getUsername());
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailExistException(userDto.getEmail());
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRoles(Set.of(Role.REGISTERED.getDescription()));

        return userRepository.save(EntityMapper.mapCreateDtoToEntity(userDto, User.class));
    }

    @Override
    public User updateUser(UserDto user) {
        return null;
    }

    @Override
    public User deleteUser(User user) {
        for (Event event : user.getParticipatingEvents()) {
            event.getParticipants().remove(user);
        }

        userRepository.delete(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
