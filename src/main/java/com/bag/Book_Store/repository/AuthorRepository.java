package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
