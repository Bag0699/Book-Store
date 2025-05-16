package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.BookResponse;
import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.model.entity.Book;
import com.bag.Book_Store.model.entity.Category;
import com.bag.Book_Store.service.AuthorService;
import com.bag.Book_Store.service.BookService;
import com.bag.Book_Store.service.CategoryService;
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

    @GetMapping("/")
    public String mostrarIndex(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Book> books = bookService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Author> authors = authorService.findAll();
        model.addAttribute("books",books);
        model.addAttribute("categories",categories);
        model.addAttribute("authors",authors);
        return "listado";
    }

    @GetMapping("/galeria/{id}")
    public String galeria(@PathVariable Long id, Model model) {

        Category categoryFind = categoryService.findById(id);
        List<Book> bookList = bookService.findAllByCategory(id);
        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("books", bookList);
        model.addAttribute("category", categoryFind);
        model.addAttribute("categories", categoryList);
        return "galeria";
    }

    @GetMapping("/libro/{id}")
    public String libro(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "libro";
        }
        return "redirect:/";
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<Book>> buscarLibros(@RequestParam("query") String query) {
        List<Book> books = bookService.searchByQuery(query);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/listado/{id}")
    public ResponseEntity<Void> borrarLibro(@PathVariable Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar/sugerencia")
    @ResponseBody
    public ResponseEntity<List<BookResponse>> obtenerSugerencias(@RequestParam("query") String query) {
        List<BookResponse> books = bookService.findAllBySuggestion(query);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/listado")
    public ResponseEntity<Book> guardarLibro(@RequestBody BookRequest request) {
        Book book = bookService.save(request);
        return ResponseEntity.ok(book);
    }
}
