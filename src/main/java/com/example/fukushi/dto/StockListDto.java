package com.example.fukushi.dto;

import com.example.fukushi.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockListDto {
    private Long id;
    private String serialCode;
    private String productName;
    private String manufactureName;
    private String status;
    private String category;
    private String location;
    private LocalDate purchasedAt;
    private String notes;

    public static StockListDto fromEntity(Stock stock) {
        return StockListDto.builder()
                .id(stock.getId())
                .serialCode(stock.getSerialCode())
                .productName(stock.getProduct().getName())
                .manufactureName(stock.getProduct().getManufacture().getName())
                .status(stock.getStatus().getDisplayName())
                .category(stock.getProduct().getCategory().getName())
                .location(stock.getLocation().getName())
                .purchasedAt(stock.getPurchasedAt())
                .notes(stock.getNotes())
                .build();
    }

//    @NotBlank(message = "管理番号は必須です")
//    @Size(max = 20, message = "管理番号は20文字以内で入力してください")
//    private String equipmentCode;
//
//    @NotBlank(message = "用具名は必須です")
//    @Size(max = 100, message = "用具名は100文字以内で入力してください")
//    private String name;
//
//    @NotNull(message = "カテゴリは必須です")
//    private EquipmentCategory category;
//
//    @NotNull(message = "状態は必須です")
//    private EquipmentStatus status;
//
//    @Size(max = 500, message = "説明は500文字以内で入力してください")
//    private String description;
}
