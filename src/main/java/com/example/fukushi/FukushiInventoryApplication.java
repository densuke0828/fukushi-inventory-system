package com.example.fukushi;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@MapperScan("com.example.fukushi.mapper")
@SpringBootApplication
public class FukushiInventoryApplication {
    public static void main(String[] args) {
        System.out.println(
                new BCryptPasswordEncoder().encode("password")
        );
        SpringApplication.run(FukushiInventoryApplication.class, args);
    }
}
