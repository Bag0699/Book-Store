package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateUserRequest;
import com.bag.Book_Store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new CreateUserRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute CreateUserRequest request,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // Si hay errores, regresa a la misma vista para mostrarlos
            return "register";
        }
        userService.saveUser(request);
        redirectAttributes.addFlashAttribute("successMessage", "¡Registro exitoso! Ahora puedes iniciar sesión.");
        return "redirect:/register";
    }
}
