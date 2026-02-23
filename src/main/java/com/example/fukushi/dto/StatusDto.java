package com.example.fukushi.dto;

import com.example.fukushi.entity.Status;
import com.example.fukushi.enums.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusDto {
        private Long id;
        private EquipmentStatus status;
        private String displayName;

    public static StatusDto fromEntity(Status status) {
        return StatusDto.builder()
                .id(status.getId())
                .status(status.getStatus())
                .displayName(status.getDisplayName())
                .build();
    }
}
