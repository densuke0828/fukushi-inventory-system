package com.example.fukushi.dto;

import com.example.fukushi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;

    public static ProductDto fromEntity(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
}