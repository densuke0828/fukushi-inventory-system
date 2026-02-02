package com.example.fukushi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 貸出履歴エンティティ
 *
 * rental_record
 *   id                    BIGINT      PK, AUTO_INCREMENT
 *   equipment_id          BIGINT      FK -> equipment.id  NOT NULL
 *   renter_name           VARCHAR(100) NOT NULL  -- 利用者名
 *   renter_contact        VARCHAR(100)           -- 連絡先
 *   rental_date           DATE         NOT NULL  -- 貸出日
 *   expected_return_date  DATE         NOT NULL  -- 返却予定日
 *   actual_return_date    DATE                   -- 実際の返却日 (NULL=未返却)
 *   notes                 VARCHAR(500)           -- 備考
 *   created_at            TIMESTAMP
 *   updated_at            TIMESTAMP
 */
@Entity
@Table(name = "rental_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @Column(nullable = false, length = 100)
    private String renterName;

    @Column(length = 100)
    private String renterContact;

    @Column(nullable = false)
    private LocalDate rentalDate;

    @Column(nullable = false)
    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;

    @Column(length = 500)
    private String notes;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public boolean isReturned() {
        return actualReturnDate != null;
    }
}
