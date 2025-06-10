package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateOrderRequest;
import com.bag.Book_Store.model.dto.response.OrderItemResponse;
import com.bag.Book_Store.model.dto.response.OrderResponse;
import com.bag.Book_Store.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> save(@Valid
                                                  @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.save(request));
    }

    @GetMapping()
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/list")
    public String listOrders(Model model) {

        List<OrderResponse> orders = orderService.findAll();

        model.addAttribute("orders", orders);
        return "admin/orders/list";
    }

    @GetMapping("/details/{id}")
    public String detailsOrder(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("orderItems", orderService.findAllOrderItemsByOrderId(id));
        return "admin/orders/details";
    }
}
