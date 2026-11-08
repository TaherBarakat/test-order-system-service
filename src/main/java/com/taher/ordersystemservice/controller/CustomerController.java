package com.taher.ordersystemservice.controller;


import com.taher.ordersystemservice.dto.request.CustomerRequest;
import com.taher.ordersystemservice.dto.response.CustomerResponse;
import com.taher.ordersystemservice.dto.response.OrderResponse;
import com.taher.ordersystemservice.service.CustomerService;
import com.taher.ordersystemservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/customers")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(
            @Valid
            @RequestBody
            CustomerRequest request
    ) {
        log.info("Incoming request: POST /internal/customers");
        return customerService.createCustomer(request);
    }

    @GetMapping("/{customerId}/orders")
    public List<OrderResponse> getOrdersByCustomer(
            @PathVariable
            @Positive(message = "ID must be greater than 0")
            Long customerId
    ) {
        log.info("Incoming request: GET /internal/customers/{}/orders", customerId);
        return orderService.getCustomerOrders(customerId);
    }
//    @GetMapping("/{id}")
//    public CustomerResponse getCustomerById(@PathVariable
//                                            @Positive(message = "ID must be greater than 0")
//                                            Long id) {
//        log.info("Incoming request: GET /internal/customers/{}", id);
//        return customerService.getCustomerById(id);
//    }
//
//    @GetMapping
//    public List<CustomerResponse> getAllCustomers() {
//        log.info("Incoming request: GET /internal/customers");
//        return customerService.getAllCustomers();
//    }


}
