package com.example.fukushi.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Long id) {
        super("在庫ID: " + id + " が見つかりません");
    }
}
