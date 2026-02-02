package com.example.fukushi.repository;

import com.example.fukushi.entity.Equipment;
import com.example.fukushi.enums.EquipmentCategory;
import com.example.fukushi.enums.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findByEquipmentCode(String equipmentCode);

    List<Equipment> findByCategory(EquipmentCategory category);

    List<Equipment> findByStatus(EquipmentStatus status);

    List<Equipment> findByCategoryAndStatus(EquipmentCategory category, EquipmentStatus status);
}
