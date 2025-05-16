package com.bag.Book_Store.service;

import com.bag.Book_Store.model.entity.Category;
import com.bag.Book_Store.repository.InMemoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final InMemoryData inMemoryData;

    public Category findById(Long id){
        return inMemoryData.categories.stream()
                .filter(category -> id.equals(category.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return inMemoryData.categories;
    }

    @Override
    public Map<String, Long> getBookCountByCategory() {
        return inMemoryData.books.stream()
                .collect(Collectors.groupingBy(book ->
                        book.getCategory().getName(), Collectors.counting()));

    }
}
