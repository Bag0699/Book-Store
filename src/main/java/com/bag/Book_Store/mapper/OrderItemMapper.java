package com.bag.Book_Store.mapper;

import com.bag.Book_Store.model.dto.request.CreateOrderItemRequest;
import com.bag.Book_Store.model.dto.response.OrderItemResponse;
import com.bag.Book_Store.model.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface OrderItemMapper {

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    OrderItem toOrderItem(CreateOrderItemRequest request);
}
