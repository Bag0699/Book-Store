package com.bag.Book_Store.controller;

import com.bag.Book_Store.service.EditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/editorials")
public class EditorialController {

    private final EditorialService editorialService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("editorials", editorialService.findAll());
        return "admin/editorials/list";
    }

}
