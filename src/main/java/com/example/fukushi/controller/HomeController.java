package com.example.fukushi.controller;

import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.service.EquipmentService;
import com.example.fukushi.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final EquipmentService equipmentService;
    private final RentalService rentalService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalEquipment", equipmentService.findAll().size());
        model.addAttribute("availableCount",
                equipmentService.findByStatus(EquipmentStatus.AVAILABLE).size());
        model.addAttribute("rentedCount",
                equipmentService.findByStatus(EquipmentStatus.RENTED).size());
        model.addAttribute("cleaningCount",
                equipmentService.findByStatus(EquipmentStatus.CLEANING).size());
        model.addAttribute("activeRentals", rentalService.findActiveRentals().size());
        return "index";
    }
}
