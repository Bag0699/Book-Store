package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.request.CreateEditorialRequest;
import com.bag.Book_Store.model.dto.response.EditorialResponse;


import java.util.List;

public interface EditorialService {

    EditorialResponse save(CreateEditorialRequest request);
    List<EditorialResponse> findAll();
    EditorialResponse findById(Long id);
    EditorialResponse update(Long id, CreateEditorialRequest request);
    void deleteById(Long id);
}
