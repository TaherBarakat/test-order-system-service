package com.taher.ordersystemservice.dto.response;

import com.taher.ordersystemservice.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponse(
        Long id,
        Long customerId,
        String productName,
        Integer quantity,
        BigDecimal price,
        OrderStatus status,
        Instant createdAt
) {
}
