package com.bag.Book_Store.controller;

import com.bag.Book_Store.service.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
