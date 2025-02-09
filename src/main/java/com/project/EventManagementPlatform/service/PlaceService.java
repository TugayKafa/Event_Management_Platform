package com.project.EventManagementPlatform.service;

import com.project.EventManagementPlatform.dto.PlaceDto;
import com.project.EventManagementPlatform.entity.Place;

import java.util.List;

public interface PlaceService {

    Place createPlace(PlaceDto placeDto);

    Place getPlaceById(Long id);

    Place getPlaceByName(String name);

    Place updatePlace(Place place);

    Place deletePlace(Long id);

    List<Place> getAllPlaces();
}
