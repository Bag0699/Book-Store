package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
