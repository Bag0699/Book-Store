package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.CategoryNotFoundException;
import com.bag.Book_Store.mapper.CategoryMapper;
import com.bag.Book_Store.model.dto.request.CreateCategoryRequest;
import com.bag.Book_Store.model.dto.response.CategoryResponse;
import com.bag.Book_Store.model.entity.Category;
import com.bag.Book_Store.repository.BookRepository;
import com.bag.Book_Store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BookRepository bookRepository;

    @Override
    public CategoryResponse save(CreateCategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse findById(Long id){
        return  categoryRepository.findById(id)
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public Map<String, Long> getBookCountByCategory() {
//        return inMemoryData.books.stream()
//                .collect(Collectors.groupingBy(book ->
//                        book.getCategory().getName(), Collectors.counting()));
        return bookRepository.findAll().stream()
                .collect(Collectors.groupingBy(book ->
                        book.getCategory().getName(), Collectors.counting()));
    }

    @Override
    public void deleteById(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse update(Long id, CreateCategoryRequest request) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(request.getName());
                    category.setDescription(request.getDescription());
                    return categoryRepository.save(category);
                })
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(CategoryNotFoundException::new);
    }
}
