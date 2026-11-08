package com.taher.ordersystemservice.service;

import com.taher.ordersystemservice.dto.request.OrderRequest;
import com.taher.ordersystemservice.dto.response.OrderResponse;
import com.taher.ordersystemservice.entity.Customer;
import com.taher.ordersystemservice.entity.Order;
import com.taher.ordersystemservice.exeption.ResourceNotFoundException;
import com.taher.ordersystemservice.mapper.OrderMapper;
import com.taher.ordersystemservice.repository.CustomerRepository;
import com.taher.ordersystemservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private OrderMapper orderMapper = new OrderMapper();

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_shouldReturnOrderResponse() {

        // Arrange
        OrderRequest request = new OrderRequest(
                1L,
                "Laptop",
                2,
                new BigDecimal("1200.00")
        );

        Customer customer = Customer.builder()
                .id(1L)
                .fullName("John Doe")
                .email("john@example.com")
                .phone("123456789")
                .build();

        Order savedOrder = Order.builder()
                .id(10L)
                .customer(customer)
                .productName("Laptop")
                .quantity(2)
                .price(new BigDecimal("1200.00"))
                .build();

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        when(orderRepository.save(any(Order.class)))
                .thenReturn(savedOrder);

        // Act
        OrderResponse response = orderService.createOrder(request);

        // Assert
        assertNotNull(response);

        ArgumentCaptor<Order> orderCaptor =
                ArgumentCaptor.forClass(Order.class);

        verify(orderRepository).save(orderCaptor.capture());

        Order capturedOrder = orderCaptor.getValue();

        assertEquals(customer, capturedOrder.getCustomer());
        assertEquals("Laptop", capturedOrder.getProductName());
        assertEquals(2, capturedOrder.getQuantity());
        assertEquals(new BigDecimal("1200.00"), capturedOrder.getPrice());

        verify(customerRepository).findById(1L);
    }

    @Test
    void createOrder_whenCustomerDoesNotExist_shouldThrowException() {

        // Arrange
        OrderRequest request = new OrderRequest(
                999L,
                "Laptop",
                2,
                new BigDecimal("1200.00")
        );

        when(customerRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.createOrder(request)
        );

        assertEquals("Customer not found: 999", exception.getMessage());

        verify(customerRepository).findById(999L);
        verify(orderRepository, never()).save(any(Order.class));
        verify(orderMapper, never()).mapToResponse(any(Order.class));
    }
}
