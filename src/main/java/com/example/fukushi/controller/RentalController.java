package com.example.fukushi.controller;

import com.example.fukushi.dto.RentalRecordDto;
import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.service.EquipmentService;
import com.example.fukushi.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final EquipmentService equipmentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rentalList",
                rentalService.findAll().stream()
                        .map(rentalService::toDto)
                        .toList());
        return "rental/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("rentalDto", RentalRecordDto.builder()
                .rentalDate(LocalDate.now())
                .expectedReturnDate(LocalDate.now().plusMonths(1))
                .build());
        model.addAttribute("availableEquipments",
                equipmentService.findByStatus(EquipmentStatus.AVAILABLE));
        return "rental/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("rentalDto") RentalRecordDto dto,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("availableEquipment",
                    equipmentService.findByStatus(EquipmentStatus.AVAILABLE));
            return "rental/form";
        }
        try {
            rentalService.rent(dto);
            redirectAttributes.addFlashAttribute("message", "貸出を登録しました");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/rental";
    }

    @PostMapping("/{id}/return")
    public String returnEquipment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            rentalService.returnEquipment(id);
            redirectAttributes.addFlashAttribute("message", "返却処理が完了しました");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/rental";
    }
}
