package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.UserNotFoundException;
import com.bag.Book_Store.mapper.UserMapper;
import com.bag.Book_Store.model.dto.request.CreateUserRequest;
import com.bag.Book_Store.model.dto.request.EditUserRequest;
import com.bag.Book_Store.model.dto.request.PasswordChangeRequest;
import com.bag.Book_Store.model.dto.response.UserResponse;
import com.bag.Book_Store.model.entity.User;
import com.bag.Book_Store.repository.UserRepository;
import com.bag.Book_Store.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(CreateUserRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegisterDate(LocalDate.now());
        user.setRole(Role.ADMIN);
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
    public UserResponse update(Long id, EditUserRequest request) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(request.getUsername());
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

    @Override
    public void changePassword(Long id, PasswordChangeRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("La nueva contraseña y la confirmación no coinciden.");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
