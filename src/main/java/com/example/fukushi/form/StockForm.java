package com.example.fukushi.form;

import com.example.fukushi.entity.Stock;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "製品名を選択してください")
    private Long productId;

    @NotNull(message = "保管場所を選択してください")
    private Long locationId;

    @NotNull(message = "商品状態を選択してください")
    private Long statusId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate purchasedAt;

    @Size(max = 500, message = "備考は500文字以内で入力してください")
    private String notes;

    public static StockForm fromEntity(Stock stock) {
        return StockForm.builder()
                .id(stock.getId())
                .productId(stock.getProduct().getId())
                .locationId(stock.getLocation().getId())
                .statusId(stock.getStatus().getId())
                .purchasedAt(stock.getPurchasedAt())
                .notes(stock.getNotes())
                .build();
    }
}
