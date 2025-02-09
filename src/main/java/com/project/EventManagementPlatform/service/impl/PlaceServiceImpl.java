package com.project.EventManagementPlatform.service.impl;

import com.project.EventManagementPlatform.dto.PlaceDto;
import com.project.EventManagementPlatform.entity.Place;
import com.project.EventManagementPlatform.exception.PlaceExistException;
import com.project.EventManagementPlatform.exception.PlaceNotFoundException;
import com.project.EventManagementPlatform.mapper.EntityMapper;
import com.project.EventManagementPlatform.repository.PlaceRepository;
import com.project.EventManagementPlatform.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Place createPlace(PlaceDto placeDto) {
        if (placeRepository.existsByNameAndLocation(placeDto.getName(), placeDto.getLocation())) {
            throw new PlaceExistException(placeDto.getName(), placeDto.getLocation());
        }

        return placeRepository.save(EntityMapper.mapCreateDtoToEntity(placeDto, Place.class));
    }

    @Override
    public Place getPlaceById(Long id) {
        Optional<Place> place = placeRepository.findById(id);

        if (!place.isPresent()) {
            throw new PlaceNotFoundException(id);
        }

        return place.get();
    }

    @Override
    public Place getPlaceByName(String name) {
        return null;
    }

    @Override
    public Place updatePlace(Place place) {
        if (placeRepository.findById(place.getId()).isEmpty()) {
            throw new PlaceNotFoundException();
        }

        return placeRepository.save(place);
    }

    @Override
    public Place deletePlace(Long id) {
        placeRepository.deleteById(id);
        return placeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Place> getAllPlaces() {
        return new ArrayList<>(List.copyOf(placeRepository.findAll()));
    }
}
