package com.project.EventManagementPlatform.utils;

import com.project.EventManagementPlatform.entity.Place;
import com.project.EventManagementPlatform.entity.Role;
import com.project.EventManagementPlatform.entity.User;
import com.project.EventManagementPlatform.repository.PlaceRepository;
import com.project.EventManagementPlatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Seed Users only if the table is empty
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // Encrypt password
                admin.setEmail("admin@example.com");
                admin.setRoles(Set.of(Role.ADMIN.getDescription()));

                userRepository.save(admin);
                System.out.println("Admin user created!");
            }

            // Seed Places only if the table is empty
            if (placeRepository.count() == 0) {
                Place conferenceRoom = new Place("Conference Room", 100, "New York");
                Place auditorium = new Place("Auditorium", 500, "Los Angeles");

                placeRepository.saveAll(Set.of(conferenceRoom, auditorium));
                System.out.println("Places added!");
            }
        };
    }
}
