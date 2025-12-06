package com.bag.Book_Store.controller;

import com.bag.Book_Store.mapper.UserMapper;
import com.bag.Book_Store.model.dto.request.CreateUserRequest;
import com.bag.Book_Store.model.dto.request.EditUserRequest;
import com.bag.Book_Store.model.dto.request.PasswordChangeRequest;
import com.bag.Book_Store.model.entity.User;
import com.bag.Book_Store.service.UserService;
import com.bag.Book_Store.util.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

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
        userService.saveAdmin(request);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("allRoles", Role.values());
        return "admin/users/form-edit";
    }

    @PostMapping("/update/{id}")
    public String upateUser(@PathVariable Long id,
                            @Valid @ModelAttribute EditUserRequest request){
        userService.update(id, request);
        return "redirect:/admin/users";
    }

    @GetMapping("/change_password/{id}")
    public String showChangePasswordForm(@PathVariable Long id, Model model){
        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setUserId(id);
        model.addAttribute("passwordForm", request);
        return "admin/users/form-change-password";
    }

    @PostMapping("/update_password")
    public String updatePassword(@Valid
                                     @ModelAttribute("passwordForm") PasswordChangeRequest request ){
        userService.changePassword(request.getUserId(), request);
        return "redirect:/admin/users";
    }
}
