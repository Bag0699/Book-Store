package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateFormarRequest;
import com.bag.Book_Store.model.dto.response.FormatResponse;
import com.bag.Book_Store.model.entity.Format;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormatMapper {

    FormatResponse toFormatResponse(Format format);

    Format toFormat(CreateFormarRequest request);
}
