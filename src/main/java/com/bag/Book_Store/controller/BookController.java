package com.bag.Book_Store.controller;

import com.bag.Book_Store.mapper.BookMapper;
import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.response.*;
import com.bag.Book_Store.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final CategoryService categoryService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final FormatService formatService;
    private final EditorialService editorialService;
    private final BookMapper bookMapper;

    @GetMapping("/")
    public String mostrarIndex(Model model) {
        List<BookResponse> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/admin/books")
    public String findAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "admin/books/list";
    }

    @GetMapping("/admin/books/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allAuthors", authorService.findAll());
        model.addAttribute("allFormats", formatService.findAll());
        model.addAttribute("allEditorials", editorialService.findAll());
        model.addAttribute("bookNew", new BookRequest());
        return "admin/books/form";
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
    public ResponseEntity<BookResponse> guardarLibro(@Valid @RequestBody BookRequest request,
                                                     MultipartFile imageFile) throws IOException {
        BookResponse book = bookService.save(request, imageFile);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/admin/books/save")
    private String addBook(@Valid @ModelAttribute("bookRequest") BookRequest book,
                           BindingResult results,
                           @RequestParam("imageFile")MultipartFile imageFile,
                           Model model) {
        if (imageFile.isEmpty()) {
            results.rejectValue("urlImg", "error.image", "La imagen de portada es obligatoria.");
            model.addAttribute("allCategories", categoryService.findAll());
            model.addAttribute("allAuthors", authorService.findAll());
            model.addAttribute("allFormats", formatService.findAll());
            model.addAttribute("allEditorials", editorialService.findAll());
            return "admin/books/form";
        }

        try {
            bookService.save(book, imageFile);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al guardar la imagen: " + e.getMessage());
            model.addAttribute("allCategories", categoryService.findAll());
            model.addAttribute("allAuthors", authorService.findAll());
            model.addAttribute("allFormats", formatService.findAll());
            model.addAttribute("allEditorials", editorialService.findAll());
            return "admin/books/form";
        }
        return "redirect:/admin/books";
    }
    @PostMapping("/admin/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        BookRequest bookRequest = bookMapper.toBookRequest(bookService.findById(id));
        model.addAttribute("bookRequest", bookRequest);
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("allAuthors", authorService.findAll());
        model.addAttribute("allFormats", formatService.findAll());
        model.addAttribute("allEditorials", editorialService.findAll());
        return "admin/books/form-edit";
    }

    @PostMapping("/admin/books/update/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("bookRequest") BookRequest book) {
        bookService.update(id, book);
        return "redirect:/admin/books";
    }
}
