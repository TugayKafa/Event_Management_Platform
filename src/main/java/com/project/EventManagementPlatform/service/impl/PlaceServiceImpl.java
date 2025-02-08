package com.project.EventManagementPlatform.service.impl;

import com.project.EventManagementPlatform.dto.PlaceDto;
import com.project.EventManagementPlatform.entity.Place;
import com.project.EventManagementPlatform.exception.UsernameExistException;
import com.project.EventManagementPlatform.mapper.EntityMapper;
import com.project.EventManagementPlatform.repository.PlaceRepository;
import com.project.EventManagementPlatform.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Place createPlace(PlaceDto placeDto) {
        if (placeRepository.existsByName(placeDto.getName())) {
            throw new UsernameExistException(placeDto.getName());
        }

        return placeRepository.save(EntityMapper.mapCreateDtoToEntity(placeDto, Place.class));
    }

    @Override
    public Place getPlaceById(Long id) {
        return null;
    }

    @Override
    public Place getPlaceByName(String name) {
        return null;
    }

    @Override
    public Place deletePlace(Place place) {
        return null;
    }

    @Override
    public List<Place> getAllPlaces() {
        return new ArrayList<>(List.copyOf(placeRepository.findAll()));
    }
}
