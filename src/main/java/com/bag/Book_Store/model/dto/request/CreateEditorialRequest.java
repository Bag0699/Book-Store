package com.bag.Book_Store.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateEditorialRequest {

    @NotEmpty(message = "Name is required")
    private String name;
}
