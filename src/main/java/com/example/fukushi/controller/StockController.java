package com.example.fukushi.controller;

import com.example.fukushi.dto.StockDto;
import com.example.fukushi.form.StockForm;
import com.example.fukushi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;
    private final StatusService statusService;
    private final LocationService locationService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String list(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long statusId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<StockDto> stockPage = stockService.findByFilter(categoryId, statusId, pageable);
        model.addAttribute("stockPage", stockPage);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("selectedStatusId", statusId);
        return "stock/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("stockForm", StockForm.builder().build());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("locations", locationService.findAll());
        return "stock/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("stockForm", stockService.findById(id));
        model.addAttribute("products", productService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("locations", locationService.findAll());
        return "stock/form";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute StockForm form,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            model.addAttribute("locations", locationService.findAll());
            return "stock/form";
        }
        stockService.save(form);
        redirectAttributes.addFlashAttribute("message", "用具を保存しました");
        return "redirect:/stock";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        stockService.delete(id);
        redirectAttributes.addFlashAttribute("message", "用具を削除しました");
        return "redirect:/stock";
    }
}