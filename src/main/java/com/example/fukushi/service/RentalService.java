package com.example.fukushi.service;

import com.example.fukushi.dto.RentalRecordDto;
import com.example.fukushi.entity.Equipment;
import com.example.fukushi.entity.RentalRecord;
import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.repository.RentalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalService {

    private final RentalRecordRepository rentalRecordRepository;
    private final EquipmentService equipmentService;

    public List<RentalRecord> findAll() {
        return rentalRecordRepository.findAll();
    }

    public RentalRecord findById(Long id) {
        return rentalRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("貸出履歴が見つかりません: ID=" + id));
    }

    public List<RentalRecord> findActiveRentals() {
        return rentalRecordRepository.findByActualReturnDateIsNull();
    }

    public List<RentalRecord> findByEquipmentId(Long equipmentId) {
        return rentalRecordRepository.findByEquipmentId(equipmentId);
    }

    @Transactional
    public RentalRecord rent(RentalRecordDto dto) {
        Equipment equipment = equipmentService.findById(dto.getEquipmentId());

        if (equipment.getStatus() != EquipmentStatus.AVAILABLE) {
            throw new IllegalStateException(
                    "この用具は現在貸出できません（状態: " + equipment.getStatus().getDisplayName() + "）");
        }

        RentalRecord record = RentalRecord.builder()
                .equipment(equipment)
                .renterName(dto.getRenterName())
                .renterContact(dto.getRenterContact())
                .rentalDate(dto.getRentalDate())
                .expectedReturnDate(dto.getExpectedReturnDate())
                .notes(dto.getNotes())
                .build();

        equipment.setStatus(EquipmentStatus.RENTED);
        return rentalRecordRepository.save(record);
    }

    @Transactional
    public RentalRecord returnEquipment(Long rentalId) {
        RentalRecord record = findById(rentalId);

        if (record.isReturned()) {
            throw new IllegalStateException("この貸出はすでに返却済みです");
        }

        record.setActualReturnDate(LocalDate.now());
        record.getEquipment().setStatus(EquipmentStatus.CLEANING);
        return rentalRecordRepository.save(record);
    }

    public RentalRecordDto toDto(RentalRecord entity) {
        return RentalRecordDto.builder()
                .id(entity.getId())
                .equipmentId(entity.getEquipment().getId())
                .equipmentName(entity.getEquipment().getName())
                .equipmentCode(entity.getEquipment().getEquipmentCode())
                .renterName(entity.getRenterName())
                .renterContact(entity.getRenterContact())
                .rentalDate(entity.getRentalDate())
                .expectedReturnDate(entity.getExpectedReturnDate())
                .actualReturnDate(entity.getActualReturnDate())
                .notes(entity.getNotes())
                .build();
    }
}
