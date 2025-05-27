package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateAuthorRequest;
import com.bag.Book_Store.model.dto.response.AuthorResponse;
import com.bag.Book_Store.model.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponse toAuthorResponse(Author author);

    Author toAuthor(CreateAuthorRequest request);
}
