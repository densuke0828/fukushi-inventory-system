package com.example.fukushi.dto;

import com.example.fukushi.enums.EquipmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDto {

    private Long id;

    @NotBlank(message = "管理番号は必須です")
    @Size(max = 20, message = "管理番号は20文字以内で入力してください")
    private String equipmentCode;

    @NotBlank(message = "用具名は必須です")
    @Size(max = 100, message = "用具名は100文字以内で入力してください")
    private String name;

    @NotNull(message = "カテゴリは必須です")
    private EquipmentCategory category;

    @NotNull(message = "状態は必須です")
    private EquipmentStatus status;

    @Size(max = 500, message = "説明は500文字以内で入力してください")
    private String description;
}
