package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateFormatRequest;
import com.bag.Book_Store.model.entity.Format;
import com.bag.Book_Store.service.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addFormat(@ModelAttribute("format") CreateFormatRequest format){
        formatService.save(format);
        return "redirect:/admin/formats";
    }
}
