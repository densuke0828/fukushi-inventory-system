package com.example.fukushi.service;

import com.example.fukushi.repository.LocationRepository;
import com.example.fukushi.repository.ProductRepository;
import com.example.fukushi.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
    @Mock
    private StockRepository stockRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private StatusService statusService;

    @InjectMocks
    private StockService stockService;

    @Test
    void findByFilter_両方nullのとき_findAllが呼ばれる() {
        when(stockRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
    }
}
