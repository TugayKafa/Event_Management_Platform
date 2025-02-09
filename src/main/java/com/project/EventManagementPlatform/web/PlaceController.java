package com.project.EventManagementPlatform.web;

import com.project.EventManagementPlatform.dto.PlaceDto;
import com.project.EventManagementPlatform.entity.Place;
import com.project.EventManagementPlatform.mapper.EntityMapper;
import com.project.EventManagementPlatform.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @GetMapping()
    public String getAllPlaces(Model model) {
        List<Place> places = placeService.getAllPlaces();
        model.addAttribute("places", places);
        return "places";
    }

    @GetMapping("/{id}")
    public String getPlaceById(@PathVariable Long id, Model model) {
        Place placeEntity = placeService.getPlaceById(id);
        PlaceDto placeDto = EntityMapper.mapEntityToDto(placeEntity, PlaceDto.class);
        model.addAttribute("place", placeDto);
        model.addAttribute("placeId", placeEntity.getId());
        return "editPlace";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return "redirect:/places";
    }


    @GetMapping("/add")
    public String getAddPlaceForm(Model model) {
        model.addAttribute("place", new PlaceDto());
        return "addPlace";
    }

    @PostMapping("/add")
    public String addNewPlace(@Valid @ModelAttribute("place") PlaceDto placeDto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "addPlace";
        }

        placeService.createPlace(placeDto);
        return "redirect:/places";
    }
}
