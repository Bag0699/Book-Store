package com.bag.Book_Store.controller;

import com.bag.Book_Store.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public String findAll(Model model) {

        model.addAttribute("authors", authorService.findAll());
        return "admin/authors/list";
    }
}
