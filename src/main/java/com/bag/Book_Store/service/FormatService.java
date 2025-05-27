package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.request.CreateFormarRequest;
import com.bag.Book_Store.model.dto.response.FormatResponse;

import java.util.List;

public interface FormatService {

    FormatResponse save(CreateFormarRequest request);
    List<FormatResponse> findAll();
    FormatResponse findById(Long id);
    FormatResponse update(Long id, CreateFormarRequest request);
    void deleteById(Long id);

}
