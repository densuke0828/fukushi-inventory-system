package com.example.fukushi.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(Long id) {
        super("不正な商品状態ID: " + id + " が入力されました");
    }
}
