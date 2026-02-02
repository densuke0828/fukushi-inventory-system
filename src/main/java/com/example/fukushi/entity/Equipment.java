package com.example.fukushi.entity;

import com.example.fukushi.enums.EquipmentCategory;
import com.example.fukushi.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 福祉用具エンティティ
 *
 * equipment
 *   id               BIGINT       PK, AUTO_INCREMENT
 *   equipment_code   VARCHAR(20)  UNIQUE NOT NULL  -- 管理番号
 *   name             VARCHAR(100) NOT NULL         -- 用具名
 *   category         VARCHAR(30)  NOT NULL         -- カテゴリ (Enum)
 *   status           VARCHAR(20)  NOT NULL         -- 状態 (Enum)
 *   description      VARCHAR(500)                  -- 説明
 *   created_at       TIMESTAMP
 *   updated_at       TIMESTAMP
 */
@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String equipmentCode;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EquipmentCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EquipmentStatus status;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RentalRecord> rentalRecords = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
