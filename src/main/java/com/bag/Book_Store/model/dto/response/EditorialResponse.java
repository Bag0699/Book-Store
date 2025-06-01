package com.bag.Book_Store.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditorialResponse {

    private Long id;
    private String name;
}
