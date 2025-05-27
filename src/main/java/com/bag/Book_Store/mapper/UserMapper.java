package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateUserRequest;
import com.bag.Book_Store.model.dto.response.UserResponse;
import com.bag.Book_Store.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User toUser(CreateUserRequest request);
}
