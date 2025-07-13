package com.bag.Book_Store.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateOrderRequest {

//    @NotNull(message = "User id cannot be null.")
//    private Long userId;

    @NotEmpty(message = "Shipping address is required")
    private String shippingAddress;

    @NotEmpty(message = "Items are required")
    @Valid
    private List<CreateOrderItemRequest> items;
}
