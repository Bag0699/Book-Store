package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
