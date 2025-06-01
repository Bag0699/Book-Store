package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.request.CreateStoreRequest;
import com.bag.Book_Store.model.dto.response.StoreResponse;

import java.util.List;

public interface StoreService {

    StoreResponse save(CreateStoreRequest request);
    List<StoreResponse> findAll();
    StoreResponse findById(Long id);
    StoreResponse update(Long id, CreateStoreRequest request);
    void deleteById(Long id);
}
