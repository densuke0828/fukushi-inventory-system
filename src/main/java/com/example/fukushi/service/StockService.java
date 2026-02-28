package com.example.fukushi.service;

import com.example.fukushi.dto.StockDto;
import com.example.fukushi.entity.Location;
import com.example.fukushi.entity.Product;
import com.example.fukushi.entity.Status;
import com.example.fukushi.entity.Stock;
import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.exception.StockNotFoundException;
import com.example.fukushi.form.StockForm;
import com.example.fukushi.repository.LocationRepository;
import com.example.fukushi.repository.ProductRepository;
import com.example.fukushi.repository.StatusRepository;
import com.example.fukushi.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private final StatusRepository statusRepository;

    public List<StockDto> findAll() {
        return stockRepository.findAll()
                .stream()
                .map(StockDto::fromEntity)
                .toList();
    }

    public StockForm findById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException(id));
        return StockForm.fromEntity(stock);
    }

    public List<Stock> findByStatus_Status(EquipmentStatus status) {
        return stockRepository.findByStatus_Status(status);
    }

    public void save(StockForm form) {
        Product product = productRepository.findById(form.getProductId()).orElseThrow();
        Location location = locationRepository.findById(form.getLocationId()).orElseThrow();
        Status status = statusRepository.findById(form.getStatusId()).orElseThrow();
        String serialCode;

        if (form.getId() == null) {
            String prefix = product.getCategory().getPrefix();
            List<Stock> existing = stockRepository.findBySerialCodeStartingWith(prefix);
            int maxNumber = existing.stream()
                    .map(s -> Integer.parseInt(s.getSerialCode().substring(prefix.length() + 1)))
                    .max(Integer::compareTo)
                    .orElse(0);
            serialCode = String.format("%s-%04d", prefix, maxNumber +1);
        } else {
            Stock existing = stockRepository.findById(form.getId()).orElseThrow(() -> new StockNotFoundException(form.getId()));
            serialCode = existing.getSerialCode();
        }

        Stock stock = Stock.builder()
                .id(form.getId())
                .serialCode(serialCode)
                .product(product)
                .location(location)
                .status(status)
                .purchasedAt(form.getPurchasedAt())
                .notes(form.getNotes())
                .build();

        stockRepository.save(stock);
    }

    public void delete(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new StockNotFoundException(id);
        }
        stockRepository.deleteById(id);
    }
}
