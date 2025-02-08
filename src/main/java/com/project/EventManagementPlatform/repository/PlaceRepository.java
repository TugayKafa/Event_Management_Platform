package com.project.EventManagementPlatform.repository;


import com.project.EventManagementPlatform.entity.Place;
import com.project.EventManagementPlatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    boolean existsByName(String username);

    Optional<User> findByName(String username);

    List<Place> findAll();
}
