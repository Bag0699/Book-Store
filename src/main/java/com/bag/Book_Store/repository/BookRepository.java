package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
