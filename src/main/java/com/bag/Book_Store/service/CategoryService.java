package com.bag.Book_Store.service;

import com.bag.Book_Store.model.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Category findById(Long id);
    List<Category> findAll();
    Map<String, Long> getBookCountByCategory();
}
