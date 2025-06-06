package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateAuthorRequest;
import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "admin/authors/form";
    }

    @PostMapping("/save")
    public String addAuthor(@ModelAttribute("author") CreateAuthorRequest author) {
        authorService.save(author);
        return "redirect:/admin/authors";
    }
}
