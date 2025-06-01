package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateEditorialRequest;
import com.bag.Book_Store.model.dto.response.EditorialResponse;
import com.bag.Book_Store.model.entity.Editorial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditorialMapper {

    EditorialResponse toEditorialResponse(Editorial editorial);

    Editorial toEditorial(CreateEditorialRequest request);
}
