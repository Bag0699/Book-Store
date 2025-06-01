package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
