package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.request.CreateFormatRequest;
import com.bag.Book_Store.model.dto.response.FormatResponse;

import java.util.List;

public interface FormatService {

    FormatResponse save(CreateFormatRequest request);
    List<FormatResponse> findAll();
    FormatResponse findById(Long id);
    FormatResponse update(Long id, CreateFormatRequest request);
    void deleteById(Long id);
}
