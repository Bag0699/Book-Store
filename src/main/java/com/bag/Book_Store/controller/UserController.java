package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateUserRequest;
import com.bag.Book_Store.model.entity.User;
import com.bag.Book_Store.service.UserService;
import com.bag.Book_Store.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin/users/list";
    }

    @GetMapping("/new")
    public String showAddUserForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", Role.values());
        return "admin/users/form";
    }

    @PostMapping("/save")
    public String addUser(@ModelAttribute CreateUserRequest request) {
        userService.save(request);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
