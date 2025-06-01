package com.bag.Book_Store.model.dto.response;

import com.bag.Book_Store.util.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class OrderResponse {
    private Long id;
    //User pero en duda.
    private String orderDate;
    private String totalAmount;
    private String shippingAddress;
    private Status status;
}
