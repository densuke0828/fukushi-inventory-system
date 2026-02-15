package com.example.fukushi.service;

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

    public List<Stock> findAll() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks;
    }

    public List<Stock> getStockByStatus(EquipmentStatus status) {
        return stockRepository.findByStatus_Status(status);
    }
}
