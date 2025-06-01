package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.response.BookResponse;
import com.bag.Book_Store.model.dto.response.BookSearchResponse;
import com.bag.Book_Store.model.entity.Book;

import java.util.List;

public interface BookService {

    List<BookResponse> findAllByCategory(Long id);
    BookResponse findById(Long id);
    void deleteById(Long id);
    List<Book> searchByQuery(String query);
    List<BookResponse> findAll();
    List<BookSearchResponse> findAllBySuggestion(String query);
    BookResponse save(BookRequest request);
}
