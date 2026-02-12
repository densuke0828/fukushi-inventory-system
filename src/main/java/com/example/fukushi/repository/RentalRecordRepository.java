package com.example.fukushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRecordRepository extends JpaRepository<RentalRecord, Long> {

    List<RentalRecord> findByEquipmentId(Long equipmentId);

    List<RentalRecord> findByActualReturnDateIsNull();

    List<RentalRecord> findByRenterNameContaining(String renterName);
}
