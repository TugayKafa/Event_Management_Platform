package com.project.EventManagementPlatform.service.impl;

import com.project.EventManagementPlatform.dto.EventDto;
import com.project.EventManagementPlatform.entity.Event;
import com.project.EventManagementPlatform.exception.EventNotFoundException;
import com.project.EventManagementPlatform.exception.NotOrganizerException;
import com.project.EventManagementPlatform.exception.PlaceNotFoundException;
import com.project.EventManagementPlatform.repository.EventRepository;
import com.project.EventManagementPlatform.service.EventService;
import com.project.EventManagementPlatform.service.PlaceService;
import com.project.EventManagementPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private static final String UPLOADS_DIR = "tmp";

    private EventRepository eventRepository;
    private PlaceService placeService;
    private UserService userService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, PlaceService placeRepository, UserService userRepository) {
        this.eventRepository = eventRepository;
        this.placeService = placeRepository;
        this.userService = userRepository;
    }

    @Override
    public Event createEvent(EventDto eventDto) {
        Event event = extractEventAttributes(eventDto);
        return eventRepository.save(event);
    }


    @Override
    public Event getEventById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }

        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        if (eventRepository.findById(event.getId()).isEmpty()) {
            throw new PlaceNotFoundException();
        }

        return eventRepository.save(event);
    }

    @Override
    public Event deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }

        if (!event.getOrganizer().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new NotOrganizerException();
        }
        eventRepository.deleteById(id);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    private Event extractEventAttributes(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new IllegalArgumentException();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        event.setOrganizer(userService.getUserByUsername(userDetails.getUsername()));

        event.setPlace(placeService.getPlaceById(eventDto.getPlaceId()));
        event.setMaxParticipants(eventDto.getMaxParticipants());

        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());

        List<String> fileNames = extractFiles(eventDto);
        event.setMaterials(fileNames);

        event.setParticipants(new LinkedHashSet<>());

        return event;
    }

    private List<String> extractFiles(EventDto eventDto) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile material : eventDto.getMaterials()) {
            if (material != null && !material.isEmpty()) {
                String filename = material.getOriginalFilename();
                if (filename != null && filename.matches("(?i)^.+\\.(jpg|png|pdf|docx|jpeg)$")) {
                    handleMultipartFile(material);
                    fileNames.add(filename);
                } else {
                    System.out.println("Invalid file format: " + filename);
                }
            }
        }
        return fileNames;
    }

    private void handleMultipartFile(MultipartFile file) {
        String filename = file.getOriginalFilename();

        if (filename == null || filename.isEmpty()) {
            // Handle invalid file name
            System.err.println("Filename is invalid or empty.");
            return;
        }

        try {
            // Log the directory path and filename
            Path uploadDir = Paths.get(UPLOADS_DIR);
            System.out.println("Upload Directory: " + uploadDir.toString());

            // Ensure the directory exists
            if (!Files.exists(uploadDir)) {
                System.out.println("Directory does not exist, creating it...");
                Files.createDirectories(uploadDir);
            }

            // Construct the full file path
            Path filePath = uploadDir.resolve(filename);

            // Log file path
            System.out.println("Saving file to: " + filePath.toAbsolutePath().toString());

            // Copy the file to the destination directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Optionally log success
            System.out.println("File saved successfully: " + filePath.toAbsolutePath().toString());

        } catch (IOException ex) {
            // Log detailed error info
            System.err.println("Error saving file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
