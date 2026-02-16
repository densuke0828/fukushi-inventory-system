package com.example.fukushi.service;

import com.example.fukushi.dto.StockListDto;
import com.example.fukushi.entity.Category;
import com.example.fukushi.entity.Stock;
import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<StockListDto> findAll() {
        return stockRepository.findAll()
                .stream()
                .map(StockListDto::fromEntity)
                .toList();
    }

    public List<Stock> findByStatus_Status(EquipmentStatus status) {
        return stockRepository.findByStatus_Status(status);
    }

//    public List<Stock> findByCategoryAndStatus(Category category, EquipmentStatus status) {
//        return stockRepository.findByCategoryAndStatus(category, status);
//    }
}
