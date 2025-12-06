package com.bag.Book_Store.model.dto.response;

import com.bag.Book_Store.util.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String registerDate;
    private Role role;
}
