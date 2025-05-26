package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
