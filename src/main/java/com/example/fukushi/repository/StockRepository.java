package com.example.fukushi.repository;

import com.example.fukushi.entity.Stock;
import com.example.fukushi.enums.EquipmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByStatus_Status(EquipmentStatus status);

    List<Stock> findBySerialCodeStartingWith(String prefix);

    Page<Stock> findByProduct_Category_Id(Long categoryId, Pageable pageable);

    Page<Stock> findByStatus_Id(Long statusId, Pageable pageable);

    Page<Stock> findByProduct_Category_IdAndStatus_Id(Long categoryId, Long statusId, Pageable pageable);
}