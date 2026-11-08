package com.taher.ordersystemservice.controller;

import com.taher.ordersystemservice.dto.request.OrderRequest;
import com.taher.ordersystemservice.dto.request.UpdateOrderStatusRequest;
import com.taher.ordersystemservice.dto.response.OrderResponse;
import com.taher.ordersystemservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/orders")
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(
            @RequestBody
            @Valid
            OrderRequest request
    ) {
        log.info("Incoming request: POST /internal/orders");
        return orderService.createOrder(request);
    }


    @PutMapping("/{id}/status")
    public OrderResponse updateOrderStatus(
            @PathVariable
            @Positive(message = "ID must be greater than 0")
            Long id,
            @RequestBody
            @Valid
            UpdateOrderStatusRequest status
    ) {
        log.info("Incoming request: PUT /internal/orders/{}/status", id);
        return orderService.updateOrderStatus(id, status);
    }
}
