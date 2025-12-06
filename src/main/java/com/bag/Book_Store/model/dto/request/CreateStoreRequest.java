package com.bag.Book_Store.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateStoreRequest {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Attention is required")
    private String attention;

    @NotEmpty(message = "Map is required")
    private String map;
}
