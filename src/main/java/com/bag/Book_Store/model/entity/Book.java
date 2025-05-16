package com.bag.Book_Store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private Author author;
    private String sinopsis;
    private BigDecimal price;
    private String isbn;
    private String description;
    private String urlImg;
    private Category category;
//  private Integer stock;
//  private Editorial editorial;
}
