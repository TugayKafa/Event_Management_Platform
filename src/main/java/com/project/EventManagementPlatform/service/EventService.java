package com.project.EventManagementPlatform.service;

import com.project.EventManagementPlatform.dto.EventDto;
import com.project.EventManagementPlatform.entity.Event;

import java.util.List;

public interface EventService {
    Event createEvent(EventDto eventDto);

    Event getEventById(Long id);

    Event updateEvent(Event event);

    Event deleteEvent(Long id);

    List<Event> getAllEvents();
}
