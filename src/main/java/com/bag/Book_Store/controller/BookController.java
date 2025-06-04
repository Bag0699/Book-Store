package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.response.*;
import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.model.entity.Book;
import com.bag.Book_Store.model.entity.Category;
import com.bag.Book_Store.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final CategoryService categoryService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final FormatService formatService;
    private final EditorialService editorialService;

    @GetMapping("/")
    public String mostrarIndex(Model model) {
        List<BookResponse> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        List<BookResponse> books = bookService.findAll();
        List<CategoryResponse> categories = categoryService.findAll();
        List<AuthorResponse> authors = authorService.findAll();
        List<FormatResponse> formats = formatService.findAll();
        List<EditorialResponse> editorials = editorialService.findAll();
        model.addAttribute("formats",formats);
        model.addAttribute("editorials",editorials);
        model.addAttribute("books",books);
        model.addAttribute("categories",categories);
        model.addAttribute("authors",authors);
        return "listado";
    }

    @GetMapping("/galeria/{id}")
    public String galeria(@PathVariable Long id, Model model) {

        CategoryResponse categoryFind = categoryService.findById(id);
        List<BookResponse> bookList = bookService.findAllByCategory(id);
        List<CategoryResponse> categoryList = categoryService.findAll();

        model.addAttribute("books", bookList);
        model.addAttribute("category", categoryFind);
        model.addAttribute("categories", categoryList);
        return "galeria";
    }

    @GetMapping("/libro/{id}")
    public String libro(@PathVariable Long id, Model model) {
        BookResponse book = bookService.findById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "libro";
        }
        return "redirect:/";
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<BookResponse>> buscarLibros(@RequestParam("query") String query) {
        List<BookResponse> books = bookService.searchByQuery(query);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/listado/{id}")
    public ResponseEntity<Void> borrarLibro(@PathVariable Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar/sugerencia")
    @ResponseBody
    public ResponseEntity<List<BookSearchResponse>> obtenerSugerencias(@RequestParam("query") String query) {
        List<BookSearchResponse> books = bookService.findAllBySuggestion(query);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/api/books")
    public ResponseEntity<BookResponse> guardarLibro(@Valid @RequestBody BookRequest request) {
        BookResponse book = bookService.save(request);
        return ResponseEntity.ok(book);
    }
}
