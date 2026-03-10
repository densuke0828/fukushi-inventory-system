package com.example.fukushi.service;

import com.example.fukushi.exception.StockNotFoundException;
import com.example.fukushi.repository.LocationRepository;
import com.example.fukushi.repository.ProductRepository;
import com.example.fukushi.repository.StatusRepository;
import com.example.fukushi.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
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
    private StatusRepository statusRepository;

    @InjectMocks
    private StockService stockService;

    // findByFilter のテスト
    @Test
    void findByFilter_両方nullのとき_findAllが呼ばれる() {
        when(stockRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        stockService.findByFilter(null, null, PageRequest.of(0, 10));

        verify(stockRepository).findAll(any(Pageable.class));
    }

    @Test
    void findByFilter_categoryIdのみ指定された時_findByProduct_CategoryIdが呼ばれる() {
        when(stockRepository.findByProduct_Category_Id(eq(1L), any(Pageable.class))).thenReturn(Page.empty());

        stockService.findByFilter(1L, null, PageRequest.of(0, 10));

        verify(stockRepository).findByProduct_Category_Id(eq(1L), any(Pageable.class));
    }

    @Test
    void findByFilter_statusIdのみ指定された時_findByStatusIdが呼ばれる() {
        when(stockRepository.findByStatus_Id(eq(1L), any(Pageable.class))).thenReturn(Page.empty());

        stockService.findByFilter(null, 1L, PageRequest.of(0, 10));

        verify(stockRepository).findByStatus_Id(eq(1L), any(Pageable.class));
    }

    @Test
    void findByFilter_両方指定された時_findByProduct_Category_IdAndStatus_Idが呼ばれる() {
        when(stockRepository.findByProduct_Category_IdAndStatus_Id(eq(1L), eq(1L), any(Pageable.class))).thenReturn(Page.empty());

        stockService.findByFilter(1L, 1L, PageRequest.of(0, 10));

        verify(stockRepository).findByProduct_Category_IdAndStatus_Id(eq(1L), eq(1L), any(Pageable.class));
    }

    @Test
    void findById_存在しないIDの時_StockNotFoundExceptionがスローされる() {
        when(stockRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(StockNotFoundException.class, () -> stockService.findById(99L));
    }
 }
