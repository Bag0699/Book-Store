package com.bag.Book_Store.model.dto;

import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.model.entity.Category;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class BookRequest {

    private Long id;
    private String title;
    private Long authorId;
    private String sinopsis;
    private BigDecimal price;
    private String isbn;
    private String description;
    private String urlImg;
    private Long categoryId;
}
