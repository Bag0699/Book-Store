package com.bag.Book_Store.service;

import com.bag.Book_Store.model.dto.request.CreateUserRequest;
import com.bag.Book_Store.model.dto.request.EditUserRequest;
import com.bag.Book_Store.model.dto.request.PasswordChangeRequest;
import com.bag.Book_Store.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse saveAdmin(CreateUserRequest request);
    UserResponse saveUser(CreateUserRequest request);
    List<UserResponse> findAll();
    UserResponse findById(Long id);
    UserResponse update(Long id, EditUserRequest request);
    void deleteById(Long id);
    void changePassword(Long id, PasswordChangeRequest request);
}
