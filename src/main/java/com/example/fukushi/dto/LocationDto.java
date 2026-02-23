package com.example.fukushi.dto;

import com.example.fukushi.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {
    private Long id;
    private String name;

    public static LocationDto fromEntity(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .build();
    }
}
