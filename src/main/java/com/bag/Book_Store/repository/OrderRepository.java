package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.dto.response.OrderWithUserDetailsResponse;
import com.bag.Book_Store.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Procedure(name = "GetOrdersByMonth")
    List<OrderWithUserDetailsResponse> getOrdersByMonth(
            @Param("p_year") Integer year,
            @Param("p_month") Integer month
    );

    @org.springframework.data.jpa.repository.Query(
            name = "GetOrdersByMonthMapping",
            nativeQuery = true
    )
    List<OrderWithUserDetailsResponse> getOrdersByMonthWithMapping(
            @Param("p_year") Integer year,
            @Param("p_month") Integer month
    );
}
