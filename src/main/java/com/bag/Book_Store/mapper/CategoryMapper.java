package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateCategoryRequest;
import com.bag.Book_Store.model.dto.response.CategoryResponse;
import com.bag.Book_Store.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);

    Category toCategory(CreateCategoryRequest request);
}
