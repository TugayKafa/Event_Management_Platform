package com.project.EventManagementPlatform.web;

import com.project.EventManagementPlatform.dto.EditEventDto;
import com.project.EventManagementPlatform.dto.EventDto;
import com.project.EventManagementPlatform.entity.Event;
import com.project.EventManagementPlatform.entity.Place;
import com.project.EventManagementPlatform.entity.User;
import com.project.EventManagementPlatform.exception.EventNotFoundException;
import com.project.EventManagementPlatform.exception.NotOrganizerException;
import com.project.EventManagementPlatform.service.EventService;
import com.project.EventManagementPlatform.service.PlaceService;
import com.project.EventManagementPlatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/events")
@Controller
public class EventController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/create")
    public String loadCreateEventPage(Model model) {
        List<Place> places = placeService.getAllPlaces();
        model.addAttribute("event", new EventDto());
        model.addAttribute("places", places);
        return "createEvent";
    }

    @PostMapping("/create")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto,
                              @RequestParam("materials") List<MultipartFile> materials,
                              BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", result);
            redirectAttributes.addFlashAttribute("event", eventDto);
            return "redirect:/events/create";  // Redirect instead of returning the view name
        }

        eventService.createEvent(eventDto);

        return "redirect:/home";
    }

    @PutMapping("{id}")
    public String updateEvent(@PathVariable Long id,
                              @Valid @ModelAttribute("event") EditEventDto eventDto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "redirect:/events/create";
        }

        Event event = new Event();
        event.setId(id);
        event.setName(eventDto.getName());
        event.setOrganizer(userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        event.setPlace(placeService.getPlaceById(eventDto.getPlaceId()));
        event.setMaterials(eventDto.getMaterials());
        event.setMaxParticipants(eventDto.getMaxParticipants());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        Set<User> participants = new LinkedHashSet<>();
        for (String username : eventDto.getParticipants()) {
            if (!username.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
                participants.add(userService.getUserByUsername(username));
            }
        }
        event.setParticipants(participants);
        eventService.updateEvent(event);

        return "redirect:/home";
    }

    @GetMapping("/{id}")
    public String loadEventDetails(@PathVariable Long id, Model model) {

        try {
            Event event = eventService.getEventById(id);
            EditEventDto eventDto = new EditEventDto();
            eventDto.setName(event.getName());
            eventDto.setOrganizer(event.getOrganizer().getUsername());
            eventDto.setMaxParticipants(event.getMaxParticipants());
            eventDto.setStartDate(event.getStartDate());
            eventDto.setEndDate(event.getEndDate());
            eventDto.setPlaceId(event.getPlace().getId());
            Set<String> usernames = new LinkedHashSet<>();
            for (User user : event.getParticipants()) {
                usernames.add(user.getUsername());
            }
            eventDto.setParticipants(usernames);
            eventDto.setMaterials(event.getMaterials());
            model.addAttribute("event", eventDto);
            model.addAttribute("places", placeService.getAllPlaces());
            model.addAttribute("eventId", id);
        } catch (EventNotFoundException ex) {
            return "error/404";
        }

        return "editEvent";
    }

    @DeleteMapping("{id}")
    public String deleteEvent(@PathVariable() Long id) {

        try {
            eventService.deleteEvent(id);
        } catch (NotOrganizerException ex) {
            return "error/403";
        } catch (EventNotFoundException ex) {
            return "error/404";
        }

        return "redirect:/home";
    }

}
