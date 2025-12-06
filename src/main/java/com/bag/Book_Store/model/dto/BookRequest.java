package com.bag.Book_Store.model.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private Long id;
    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Sinopsis is required")
    private String sinopsis;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @NotEmpty(message = "Isbn is required")
    private String isbn;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotEmpty(message = "UrlImg is required")
    private String urlImg;

    @NotNull(message = "Stock cannot be null")
    private Integer stock;

    @NotEmpty(message = "Dimension is required")
    private String dimension;

    @NotNull(message = "Author id cannot be null")
    private Long authorId;

    @NotNull(message = "Category id cannot be null")
    private Long categoryId;

    @NotNull(message = "Editorial id cannot be null")
    private Long editorialId;

    @NotNull(message = "Format id cannot be null")
    private Long formatId;
}
