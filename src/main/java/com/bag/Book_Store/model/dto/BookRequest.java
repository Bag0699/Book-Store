package com.bag.Book_Store.model.dto;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class BookRequest {

    private String title;
    private String sinopsis;
    private BigDecimal price;
    private String isbn;
    private String description;
    private String urlImg;
    private Integer stock;
    private String dimension;
    private Long authorId;
    private Long categoryId;
    private Long editorialId;
    private Long formatId;
}
