package com.example.fukushi.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    List<Equipment> findAll();
}
