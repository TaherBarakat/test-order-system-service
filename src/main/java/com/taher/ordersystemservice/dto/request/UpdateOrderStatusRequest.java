package com.taher.ordersystemservice.dto.request;

import com.taher.ordersystemservice.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
        @NotNull(message = "Order status is required")
        OrderStatus status
) {
}
