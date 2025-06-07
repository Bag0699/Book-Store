package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateEditorialRequest;
import com.bag.Book_Store.model.entity.Editorial;
import com.bag.Book_Store.service.EditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String showAddEditorialForm(Model model) {
        model.addAttribute("editorial", new Editorial());
        return "admin/editorials/form";
    }

    @PostMapping("/save")
    public String addEditorial(@ModelAttribute("editorial") CreateEditorialRequest editorial) {
        editorialService.save(editorial);
        return "redirect:/admin/editorials";
    }

    @PostMapping("/delete/{id}")
    public String deleteEditorial(@PathVariable Long id) {
        editorialService.deleteById(id);
        return "redirect:/admin/editorials";
    }
}
