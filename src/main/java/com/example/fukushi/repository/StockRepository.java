package com.example.fukushi.repository;

import com.example.fukushi.entity.Category;
import com.example.fukushi.entity.Stock;
import com.example.fukushi.enums.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByStatus_Status(EquipmentStatus status);

//    List<Stock> findByCategoryAndStatus(Category category, EquipmentStatus status);
}
