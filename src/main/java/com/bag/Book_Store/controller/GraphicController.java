package com.bag.Book_Store.controller;

import com.bag.Book_Store.service.AuthorService;
import com.bag.Book_Store.service.AuthorServiceImpl;
import com.bag.Book_Store.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GraphicController {

    private final CategoryService categoryService;
    private final AuthorService authorService;

    @GetMapping("/graficos")
    public String graficos() {
        return "graficos";
    }

    @GetMapping("/graficos/circle")
    @ResponseBody
    private ResponseEntity<Map<String, Long>> getBookCountBtyCategory() {
        Map<String, Long> data = categoryService.getBookCountByCategory();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/graficos/bar")
    @ResponseBody
    private ResponseEntity<Map<String, Long>> getBookCountByAuthor() {
        Map<String, Long> data = authorService.getBookCountByAuthor();
        return ResponseEntity.ok(data);
    }
}
