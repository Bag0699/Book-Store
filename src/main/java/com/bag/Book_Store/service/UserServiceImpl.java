package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.UserNotFoundException;
import com.bag.Book_Store.mapper.UserMapper;
import com.bag.Book_Store.model.dto.request.CreateUserRequest;
import com.bag.Book_Store.model.dto.response.UserResponse;
import com.bag.Book_Store.model.entity.User;
import com.bag.Book_Store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse save(CreateUserRequest request) {
        User user = userMapper.toUser(request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserResponse update(Long id, CreateUserRequest request) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(request.getUsername());
                    user.setPassword(request.getPassword());
                    user.setEmail(request.getEmail());
                    user.setFullName(request.getFullName());
                    return userRepository.save(user);
                })
                .map(userMapper::toUserResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
