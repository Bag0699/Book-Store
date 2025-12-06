package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateFormatRequest;
import com.bag.Book_Store.model.entity.Format;
import com.bag.Book_Store.service.FormatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/formats")
public class FormatController {

    private final FormatService formatService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("formats", formatService.findAll());
        return "admin/formats/list";
    }

    @GetMapping("/new")
    public String showAddFormatForm(Model model){
        model.addAttribute("format", new Format());
        return "admin/formats/form";
    }

    @PostMapping("/save")
    public String addFormat(@Valid @ModelAttribute("format") CreateFormatRequest format){
        formatService.save(format);
        return "redirect:/admin/formats";
    }

    @PostMapping("/delete/{id}")
    public String deleteFormat(@PathVariable Long id){
        formatService.deleteById(id);
        return "redirect:/admin/formats";
    }

    @GetMapping("/edit/{id}")
    public String showEditFormatForm(@PathVariable Long id, Model model){
        model.addAttribute("format", formatService.findById(id));
        return "admin/formats/form";
    }

    @PostMapping("/update/{id}")
    public String editFormat(@PathVariable Long id,
                             @Valid @ModelAttribute("format") CreateFormatRequest format){
        formatService.update(id, format);
        return "redirect:/admin/formats";
    }
}
