package com.bag.Book_Store.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    private String sinopsis;
    private BigDecimal price;
    private String isbn;
    private String description;
    @Column(name = "url_img")
    private String urlImg;

    private Integer stock;
    private String dimension;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "format_id")
    private Format format;
}
