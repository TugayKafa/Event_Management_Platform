package com.project.EventManagementPlatform.repository;

import com.project.EventManagementPlatform.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByNameContainingIgnoreCaseAndPlace_NameContainingIgnoreCaseAndOrganizer_UsernameContainingIgnoreCase(String name, String placeName, String organizer);
}
