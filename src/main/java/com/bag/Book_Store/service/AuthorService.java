package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.response.AuthorResponse;
import com.bag.Book_Store.model.entity.Author;

import java.util.List;
import java.util.Map;

public interface AuthorService {

    Map<String, Long> getBookCountByAuthor();
    List<AuthorResponse> findAll();
    AuthorResponse findById(Long id);
}
