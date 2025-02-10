package com.project.EventManagementPlatform.web;

import com.project.EventManagementPlatform.dto.EventDto;
import com.project.EventManagementPlatform.entity.Place;
import com.project.EventManagementPlatform.service.EventService;
import com.project.EventManagementPlatform.service.PlaceService;
import com.project.EventManagementPlatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

}
