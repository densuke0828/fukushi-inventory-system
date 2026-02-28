package com.example.fukushi.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StockNotFoundException.class)
    public String handleStockNotFound(Model model) {
        model.addAttribute("message", "在庫データが見つかりませんでした");
        return "error/404";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFound(Model model) {
        model.addAttribute("message", "予期しないエラーが発生しました");
        return "error/500";
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public String handleLocationNotFound(Model model) {
        model.addAttribute("message", "予期しないエラーが発生しました");
        return "error/500";
    }

    @ExceptionHandler(StatusNotFoundException.class)
    public String handleStatusNotFound(Model model) {
        model.addAttribute("message", "予期しないエラーが発生しました");
        return "error/500";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model) {
        model.addAttribute("message", "予期しないエラーが発生しました");
        return "error/500";
    }
}
