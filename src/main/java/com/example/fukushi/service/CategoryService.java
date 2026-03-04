package com.example.fukushi.service;

import com.example.fukushi.dto.CategoryDto;
import com.example.fukushi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::fromEntity)
                .toList();
    }
}
