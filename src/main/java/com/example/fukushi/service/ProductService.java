package com.example.fukushi.service;

import com.example.fukushi.dto.ProductDto;
import com.example.fukushi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto::fromEntity)
                .toList();
    }
}
