package com.bag.Book_Store.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderItemResponse {

    private Long id;
//    private OrderResponse order;
    private BookResponse book;
    private Integer quantity;
    private BigDecimal unitePrice;
    private BigDecimal subTotal;
}
