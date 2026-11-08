package com.taher.ordersystemservice.service;


import com.taher.ordersystemservice.dto.request.OrderRequest;
import com.taher.ordersystemservice.dto.request.UpdateOrderStatusRequest;
import com.taher.ordersystemservice.dto.response.OrderResponse;
import com.taher.ordersystemservice.exeption.ResourceNotFoundException;
import com.taher.ordersystemservice.entity.Customer;
import com.taher.ordersystemservice.entity.Order;
import com.taher.ordersystemservice.mapper.OrderMapper;
import com.taher.ordersystemservice.repository.CustomerRepository;
import com.taher.ordersystemservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        Customer customer = customerRepository
                .findById(request.customerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found: " + request.customerId()
                        )
                );

        Order order = Order.builder()
                .customer(customer)
                .productName(request.productName())
                .quantity(request.quantity())
                .price(request.price())
                .build();

        Order savedOrder = orderRepository.save(order);

        return orderMapper.mapToResponse(savedOrder);
    }

    public List<OrderResponse> getCustomerOrders(Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(
                    "Customer not found: " + customerId
            );
        }

        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(orderMapper::mapToResponse)
                .toList();
    }

    public OrderResponse updateOrderStatus(
            Long orderId,
            UpdateOrderStatusRequest request
    ) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found: " + orderId
                        )
                );

        order.setStatus(request.status());

        return orderMapper.mapToResponse(orderRepository.save(order));
    }

}
