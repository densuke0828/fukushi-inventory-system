package com.example.fukushi.service;

import com.example.fukushi.dto.StatusDto;
import com.example.fukushi.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;
    public List<StatusDto> findAll() {
        return statusRepository.findAll()
                .stream()
                .map(StatusDto::fromEntity)
                .toList();

    }
}
