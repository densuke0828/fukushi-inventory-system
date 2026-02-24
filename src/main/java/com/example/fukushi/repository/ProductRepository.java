package com.example.fukushi.repository;

import com.example.fukushi.dto.ProductDto;
import com.example.fukushi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
