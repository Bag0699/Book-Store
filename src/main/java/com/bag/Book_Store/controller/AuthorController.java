package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateAuthorRequest;
import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/admin/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "admin/authors/form";
    }

    @PostMapping("/update/{id}")
    public String editAuthor(@PathVariable Long id,
                             @Valid @ModelAttribute("author") CreateAuthorRequest author) {
        authorService.update(id, author);
        return "redirect:/admin/authors";
    }
}
