package com.example.fukushi.service;

import com.example.fukushi.dto.EquipmentDto;
import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("用具が見つかりません: ID=" + id));
    }

    public List<Equipment> findByCategory(EquipmentCategory category) {
        return equipmentRepository.findByCategory(category);
    }

    public List<Equipment> findByStatus(EquipmentStatus status) {
        return equipmentRepository.findByStatus(status);
    }

    public List<Equipment> findByCategoryAndStatus(EquipmentCategory category, EquipmentStatus status) {
        return equipmentRepository.findByCategoryAndStatus(category, status);
    }

    @Transactional
    public Equipment save(EquipmentDto dto) {
        Equipment equipment;
        if (dto.getId() != null) {
            equipment = findById(dto.getId());
        } else {
            equipment = new Equipment();
        }
        equipment.setEquipmentCode(dto.getEquipmentCode());
        equipment.setName(dto.getName());
        equipment.setCategory(dto.getCategory());
        equipment.setStatus(dto.getStatus());
        equipment.setDescription(dto.getDescription());
        return equipmentRepository.save(equipment);
    }

    @Transactional
    public void updateStatus(Long id, EquipmentStatus status) {
        Equipment equipment = findById(id);
        equipment.setStatus(status);
    }

    @Transactional
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }

    public EquipmentDto toDto(Equipment entity) {
        return EquipmentDto.builder()
                .id(entity.getId())
                .equipmentCode(entity.getEquipmentCode())
                .name(entity.getName())
                .category(entity.getCategory())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .build();
    }
}
