package com.example.fukushi.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("不正な製品ID: " + id + " が入力されました");
    }
}
