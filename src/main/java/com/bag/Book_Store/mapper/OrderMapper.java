package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateOrderRequest;
import com.bag.Book_Store.model.dto.response.OrderResponse;
import com.bag.Book_Store.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OrderMapper {

    @Mapping(target = "orderDate", expression = "java(mapFormatOrderDate(order))")
    OrderResponse toOrderResponse(Order order);

    Order toOrder(CreateOrderRequest request);

    default String mapFormatOrderDate(Order order){
        return order
                .getOrderDate()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
