package com.bag.Book_Store.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateOrderItemRequest {

    @NotNull(message = "Book id cannot be null. ")
    private Long bookId;

    @NotNull(message = "Quantity cannot be null.")
    private Integer quantity;
}
