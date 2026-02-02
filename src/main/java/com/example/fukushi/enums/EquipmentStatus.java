package com.example.fukushi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EquipmentStatus {

    AVAILABLE("在庫"),
    RENTED("貸出中"),
    CLEANING("洗浄中");

    private final String displayName;
}
