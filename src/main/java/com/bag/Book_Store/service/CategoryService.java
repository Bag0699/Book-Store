package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.response.CategoryResponse;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    CategoryResponse findById(Long id);
    List<CategoryResponse> findAll();
    Map<String, Long> getBookCountByCategory();
}
