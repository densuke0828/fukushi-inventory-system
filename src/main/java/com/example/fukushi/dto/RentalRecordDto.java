//package com.example.fukushi.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.LocalDate;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class RentalRecordDto {
//
//    private Long id;
//
//    @NotNull(message = "用具は必須です")
//    private Long equipmentId;
//
//    private String equipmentName;
//    private String equipmentCode;
//
//    @NotBlank(message = "利用者名は必須です")
//    @Size(max = 100, message = "利用者名は100文字以内で入力してください")
//    private String renterName;
//
//    @Size(max = 100, message = "連絡先は100文字以内で入力してください")
//    private String renterContact;
//
//    @NotNull(message = "貸出日は必須です")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate rentalDate;
//
//    @NotNull(message = "返却予定日は必須です")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate expectedReturnDate;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate actualReturnDate;
//
//    @Size(max = 500, message = "備考は500文字以内で入力してください")
//    private String notes;
//}
