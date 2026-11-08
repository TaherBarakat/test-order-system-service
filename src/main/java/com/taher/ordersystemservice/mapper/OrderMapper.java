package com.taher.ordersystemservice.mapper;

import com.taher.ordersystemservice.dto.response.OrderResponse;
import com.taher.ordersystemservice.entity.Order;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse mapToResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
