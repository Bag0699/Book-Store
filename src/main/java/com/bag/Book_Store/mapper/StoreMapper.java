package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateStoreRequest;
import com.bag.Book_Store.model.dto.response.StoreResponse;
import com.bag.Book_Store.model.entity.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreResponse toStoreResponse(Store store);

    Store toStore(CreateStoreRequest request);
}
