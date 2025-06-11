package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateOrderRequest;
import com.bag.Book_Store.model.dto.response.OrderResponse;
import com.bag.Book_Store.model.dto.response.OrderWithUserDetailsResponse;
import com.bag.Book_Store.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("/order-by-month")
    public String orderByMonth(@RequestParam(name = "year", required = false) Integer year,
                               @RequestParam(name = "month", required = false) Integer month,
                               Model model) {

        if (year == null) {
            year = LocalDate.now().getYear();
        }
        if (month == null) {
            month = LocalDate.now().getMonthValue();
        }

        List<OrderWithUserDetailsResponse> orders = orderService.findAllOrderByMonth(year, month);
        model.addAttribute("orders", orders);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("months", getMonthsName());
        model.addAttribute("years", getYears());
        return "admin/orders/order-by-month";
    }

    private List<String> getMonthsName() {
        return Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    }

    private List<Integer> getYears() {
        int currentYear = java.time.LocalDate.now().getYear();
        List<Integer> years = new ArrayList<>();
        for (int i = currentYear; i > currentYear - 5; i--) {
            years.add(i);
        }
        return years;
    }
}
