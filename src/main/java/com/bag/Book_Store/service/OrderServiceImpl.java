package com.bag.Book_Store.service;

import com.bag.Book_Store.exception.BookNotFoundException;
import com.bag.Book_Store.exception.OrderNotFoundException;
import com.bag.Book_Store.exception.UserNotFoundException;
import com.bag.Book_Store.mapper.OrderItemMapper;
import com.bag.Book_Store.mapper.OrderMapper;
import com.bag.Book_Store.model.dto.request.CreateOrderRequest;
import com.bag.Book_Store.model.dto.response.OrderItemResponse;
import com.bag.Book_Store.model.dto.response.OrderResponse;
import com.bag.Book_Store.model.dto.response.OrderWithUserDetailsResponse;
import com.bag.Book_Store.model.entity.Book;
import com.bag.Book_Store.model.entity.Order;
import com.bag.Book_Store.model.entity.OrderItem;
import com.bag.Book_Store.model.entity.User;
import com.bag.Book_Store.repository.BookRepository;
import com.bag.Book_Store.repository.OrderItemRepository;
import com.bag.Book_Store.repository.OrderRepository;
import com.bag.Book_Store.repository.UserRepository;
import com.bag.Book_Store.util.Status;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public OrderResponse save(Long userId, CreateOrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if(request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("La orden esta vacía.");
        }

        //Validar y búsqueda del usuario y creación del order
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setShippingAddress(request.getShippingAddress());
        order.setStatus(Status.PENDING);

        List<OrderItem> createOrderItems = request.getItems().stream()
                .map( itemRequest -> {
                    Book book = bookRepository.findById(itemRequest.getBookId())
                            .orElseThrow(BookNotFoundException::new);
                    decreaseBookStock(book, itemRequest.getQuantity());
                    BigDecimal unitPrice = book.getPrice();
                    BigDecimal subTotal = unitPrice.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setBook(book);
                    orderItem.setUnitePrice(unitPrice);
                    orderItem.setQuantity(itemRequest.getQuantity());
                    orderItem.setSubTotal(subTotal);
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());
        order.setOrderItems(createOrderItems);
        //Calcular el total
        BigDecimal totalAmount = createOrderItems.stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);

        System.out.println("Nombre completo: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Monto total: " + savedOrder.getTotalAmount().doubleValue());
        System.out.println("Numero de orden: " + savedOrder.getId());

        //Correo de confirmación de la compra
        try {
            String fullName = user.getFullName();

            Boolean emailSent = emailService.sendOrderConfirmationEmail(
                    user.getEmail(),
                    savedOrder.getId().toString(),
                    savedOrder.getTotalAmount().doubleValue(),
                    user.getFullName()
            );
            if (emailSent) {
                System.out.println("Correo de confirmación de orden #" + savedOrder.getId() + " enviado al usuario " + user.getEmail());
            } else {
                System.err.println("Advertencia: No se pudo enviar el correo de confirmación para la orden #" + savedOrder.getId());
            }
        } catch (Exception e) {
            System.err.println("Error inesperado al intentar enviar el correo para la orden #" + savedOrder.getId() + ": " + e.getMessage());
            e.printStackTrace();
        }

        return orderMapper.toOrderResponse(savedOrder);
    }

    private void decreaseBookStock(Book book, Integer quantity) {
        if(book.getStock() < quantity) {
            throw new IllegalArgumentException("No hay suficientes libros en stock");
        }
        book.setStock(book.getStock() - quantity);
        bookRepository.save(book);
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public OrderResponse update(Long id, CreateOrderRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        if(!orderRepository.existsById(id)) {
            throw new OrderNotFoundException();
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderItemResponse> findAllOrderItemsByOrderId(Long orderId) {
        if(!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException();
        }
        return orderItemRepository.findAllByOrder_Id(orderId)
                .stream()
                .map(orderItemMapper::toOrderItemResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<OrderWithUserDetailsResponse> findAllOrderByMonth(Integer year, Integer month) {
//        return orderRepository.getOrdersByMonth(year, month);
        return orderRepository.getOrdersByMonthWithMapping(year, month);

    }
}
