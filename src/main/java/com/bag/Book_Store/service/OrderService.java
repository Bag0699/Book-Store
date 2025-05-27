package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.request.CreateOrderRequest;
import com.bag.Book_Store.model.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse save(CreateOrderRequest request);
    List<OrderResponse> findAll();
    OrderResponse findById(Long id);
    OrderResponse update(Long id, CreateOrderRequest request);
    void deleteById(Long id);
}
