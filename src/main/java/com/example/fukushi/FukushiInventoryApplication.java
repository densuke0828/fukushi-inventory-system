package com.example.fukushi;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.fukushi.mapper")
@SpringBootApplication
public class FukushiInventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(FukushiInventoryApplication.class, args);
    }
}
