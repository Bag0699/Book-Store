package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.BookRequest;
import com.bag.Book_Store.model.dto.response.BookResponse;
import com.bag.Book_Store.model.dto.response.BookSearchResponse;
import com.bag.Book_Store.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class
        , EditorialMapper.class, FormatMapper.class})
public interface BookMapper {

    BookSearchResponse toBookSearchResponse(Book book);
    @Mapping(source = "urlImg", target = "urlImg")
    BookResponse toBookResponse(Book book);
    Book toBook(BookRequest request);
}
