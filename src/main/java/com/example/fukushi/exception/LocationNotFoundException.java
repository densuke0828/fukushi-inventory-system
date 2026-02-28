package com.example.fukushi.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super("不正な保管場所ID: " + id + " が入力されました");
    }
}
