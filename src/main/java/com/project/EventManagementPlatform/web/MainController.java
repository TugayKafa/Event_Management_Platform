package com.project.EventManagementPlatform.web;

import com.project.EventManagementPlatform.entity.Event;
import com.project.EventManagementPlatform.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private EventService eventService;

    @GetMapping()
    public String showHomePageOnDefaultEndpoint(Model model) {
        List<Event> events = eventService.getAllEvents();
        events.sort(Comparator.comparing(Event::getStartDate).thenComparing(Event::getEndDate));
        model.addAttribute("events", events);
        return "home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Event> events = eventService.getAllEvents();
        events.sort(Comparator.comparing(Event::getStartDate).thenComparing(Event::getEndDate));
        model.addAttribute("events", events);
        return "home";
    }
}
