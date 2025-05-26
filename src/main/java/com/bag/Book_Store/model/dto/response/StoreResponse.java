package com.bag.Book_Store.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreResponse {

    private Long id;
    private String name;
    private String address;
    private String attention;
    private String map;
}
