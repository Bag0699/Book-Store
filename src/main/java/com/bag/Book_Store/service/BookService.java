package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.BookResponse;
import com.bag.Book_Store.model.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllByCategory(Long id);
    Book findById(Long id);
    void deleteById(Long id);
    List<Book> searchByQuery(String query);
    List<Book> findAll();
    List<BookResponse> findAllBySuggestion(String query);
    Book save(BookRequest request);
}
