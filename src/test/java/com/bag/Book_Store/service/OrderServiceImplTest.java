package com.bag.Book_Store.service;

import com.bag.Book_Store.mapper.OrderItemMapper;
import com.bag.Book_Store.mapper.OrderMapper;
import com.bag.Book_Store.model.dto.request.CreateOrderItemRequest;
import com.bag.Book_Store.model.dto.request.CreateOrderRequest;
import com.bag.Book_Store.model.dto.response.OrderResponse;
import com.bag.Book_Store.model.dto.response.UserResponse;
import com.bag.Book_Store.model.entity.Book;
import com.bag.Book_Store.model.entity.Order;
import com.bag.Book_Store.model.entity.User;
import com.bag.Book_Store.repository.BookRepository;
import com.bag.Book_Store.repository.OrderItemRepository;
import com.bag.Book_Store.repository.OrderRepository;
import com.bag.Book_Store.repository.UserRepository;
import com.bag.Book_Store.util.Role;
import com.bag.Book_Store.util.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private EmailService emailService;

    @Mock
    private OrderItemMapper orderItemMapper;
    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    private User testUser;
    private Book testBook;
    private CreateOrderRequest validRequest;
    private Order savedOrder;
    private OrderResponse expectedResponse;

    @BeforeEach
    void setUp() {
        // GIVEN: Inicialización de objetos comunes

        // 1. Usuario
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("username");
        testUser.setPassword("");
        testUser.setFullName("Juan Perez");
        testUser.setEmail("juan.perez@test.com");
        testUser.setRegisterDate(LocalDate.now());
        testUser.setRole(Role.USER);

        // 2. Libro
        testBook = new Book();
        testBook.setId(10L);
        testBook.setStock(50);
        testBook.setPrice(new BigDecimal("15.00")); // Precio del libro

        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .username("username")
                .email("juan.perez@test.com")
                .fullName("Juan Perez")
                .registerDate(LocalDate.now().toString())
                .role(Role.USER)
                .build();

        // 3. Solicitud de orden válida
        CreateOrderItemRequest itemRequest = CreateOrderItemRequest.builder()
                .bookId(10L)
                .quantity(2)
                .build(); // 2 unidades del libro 10L
        validRequest = CreateOrderRequest.builder()
                .items(List.of(itemRequest))
                .shippingAddress("Calle Falsa 123")
                .build();

        // 4. Orden simulada que el repositorio 'guarda'
        savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setUser(testUser);
        savedOrder.setStatus(Status.PENDING);
        savedOrder.setTotalAmount(new BigDecimal("30.00")); // 2 * 15.00
        savedOrder.setOrderDate(LocalDate.now());

        // 5. Respuesta esperada
        expectedResponse = OrderResponse.builder()
                .id(1L)
                .user(userResponse)
                .orderDate(savedOrder.getOrderDate().toString())
                .totalAmount(savedOrder.getTotalAmount().toString())
                .shippingAddress(savedOrder.getShippingAddress())
                .status(Status.PENDING)
                .build();
    }

    @Test
    void givenValidOrderRequestAndSufficientStock_whenSave_thenShouldSaveOrderDecreaseStockAndSendEmail() {
        // GIVEN: (Configuración de Mocks para el éxito)
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(bookRepository.findById(10L)).thenReturn(Optional.of(testBook));
        // Mockeamos orderRepository.save para devolver la orden que simula el resultado final
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
        // Mockeamos el mapeo de respuesta
        when(orderMapper.toOrderResponse(any(Order.class))).thenReturn(expectedResponse);
        // Mockeamos el servicio de email para simular el éxito
        when(emailService.sendOrderConfirmationEmail(anyString(), anyString(), anyDouble(), anyString())).thenReturn(true);

        // WHEN: Ejecutar el método a probar
        OrderResponse result = orderServiceImpl.save(1L, validRequest);

        // THEN: Verificaciones
        // 1. Debe devolver la respuesta esperada
        assertNotNull(result);

        // 2. Debe guardar la orden
        verify(orderRepository, times(1)).save(any(Order.class));

        // 3. Debe decrementar el stock del libro (50 - 2 = 48)
        assertEquals(48, testBook.getStock());

        // 4. Debe intentar enviar el correo de confirmación
        verify(emailService, times(1)).sendOrderConfirmationEmail(
                eq(testUser.getEmail()),
                eq(savedOrder.getId().toString()),
                eq(savedOrder.getTotalAmount().doubleValue()),
                eq(testUser.getFullName())
        );
    }

}