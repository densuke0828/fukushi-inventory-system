package com.example.fukushi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EquipmentCategory {

    WHEELCHAIR("車椅子"),
    SPECIAL_BED("特殊寝台"),
    WALKER("歩行器"),
    HANDRAIL("手すり"),
    SLOPE("スロープ"),
    LIFT("移動用リフト"),
    PRESSURE_MATTRESS("床ずれ防止用具"),
    OTHER("その他");

    private final String displayName;
}
