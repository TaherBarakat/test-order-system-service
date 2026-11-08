package com.taher.ordersystemservice.mapper;

import com.taher.ordersystemservice.dto.response.CustomerResponse;
import com.taher.ordersystemservice.entity.Customer;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerResponse mapToResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt()
        );
    }
}
