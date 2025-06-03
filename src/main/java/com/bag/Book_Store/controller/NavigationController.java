package com.bag.Book_Store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NavigationController {

    @GetMapping("/somos")
    public String somos() {
        return "somos";
    }

    @GetMapping("/condiciones")
    public String condiciones() {
        return "condiciones";
    }

    @GetMapping({"/preguntas"})
    public String preguntas() {
        return "preguntas";
    }

    @GetMapping("/cart")
    public String showCartPage() {
        return "cart";
    }

}
