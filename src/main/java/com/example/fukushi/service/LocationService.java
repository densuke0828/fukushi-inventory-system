package com.example.fukushi.service;

import com.example.fukushi.dto.LocationDto;
import com.example.fukushi.entity.Location;
import com.example.fukushi.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<LocationDto> findAll() {
        return locationRepository.findAll()
                .stream()
                .map(LocationDto::fromEntity)
                .toList();
    }
}
