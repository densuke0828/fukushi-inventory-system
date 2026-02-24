package com.example.fukushi.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockForm {
    private Long id;
    private Long productId;
    private Long locationId;
    private Long statusId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate purchasedAt;
    private String notes;
}
