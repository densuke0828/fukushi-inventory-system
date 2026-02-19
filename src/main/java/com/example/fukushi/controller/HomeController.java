package com.example.fukushi.controller;

import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final StockService stockService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("stockCount", stockService.findAll().size());
        model.addAttribute("availableCount",
                stockService.findByStatus_Status(EquipmentStatus.AVAILABLE).size());
        model.addAttribute("rentedCount",
                stockService.findByStatus_Status(EquipmentStatus.RENTED).size());
        model.addAttribute("cleaningCount",
                stockService.findByStatus_Status(EquipmentStatus.CLEANING).size());
        model.addAttribute("repairCount",
                stockService.findByStatus_Status(EquipmentStatus.REPAIR).size());
        return "index";
    }
}
