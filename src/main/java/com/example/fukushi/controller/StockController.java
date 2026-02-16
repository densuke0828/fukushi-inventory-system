package com.example.fukushi.controller;

import com.example.fukushi.dto.EquipmentDto;
import com.example.fukushi.entity.Category;
import com.example.fukushi.enums.EquipmentStatus;
import com.example.fukushi.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public String list(@RequestParam(required = false) Category category,
                       @RequestParam(required = false) EquipmentStatus status,
                       Model model) {
        if (category != null && status != null) {
            model.addAttribute("stocks",
                    stockService.findByCategoryAndStatus(category, status));
        } else if (category != null) {
            model.addAttribute("stocks", equipmentService.findByCategory(category));
        } else if (status != null) {
            model.addAttribute("stocks", equipmentService.findByStatus(status));
        } else {
            model.addAttribute("stocks", equipmentService.findAll());
        }
        model.addAttribute("categories", EquipmentCategory.values());
        model.addAttribute("statuses", EquipmentStatus.values());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedStatus", status);
        return "equipment/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("equipmentDto", EquipmentDto.builder()
                .status(EquipmentStatus.AVAILABLE)
                .build());
        model.addAttribute("categories", EquipmentCategory.values());
        model.addAttribute("statuses", EquipmentStatus.values());
        return "equipment/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("equipmentDto", equipmentService.toDto(equipmentService.findById(id)));
        model.addAttribute("categories", EquipmentCategory.values());
        model.addAttribute("statuses", EquipmentStatus.values());
        return "equipment/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute EquipmentDto equipmentDto,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", EquipmentCategory.values());
            model.addAttribute("statuses", EquipmentStatus.values());
            return "equipment/form";
        }
        equipmentService.save(equipmentDto);
        redirectAttributes.addFlashAttribute("message", "用具を保存しました");
        return "redirect:/equipment";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        equipmentService.delete(id);
        redirectAttributes.addFlashAttribute("message", "用具を削除しました");
        return "redirect:/equipment";
    }
}
