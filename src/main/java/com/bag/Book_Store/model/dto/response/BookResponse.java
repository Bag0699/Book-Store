package com.bag.Book_Store.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String sinopsis;
    private BigDecimal price;
    private String isbn;
    private String description;
    private String urlImg;
    private Integer stock;
    private String dimension;
    private CategoryResponse category;
    private AuthorResponse author;
    private EditorialResponse editorial;
    private FormatResponse format;
}
