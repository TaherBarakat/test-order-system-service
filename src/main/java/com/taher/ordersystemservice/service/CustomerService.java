package com.taher.ordersystemservice.service;


import com.taher.ordersystemservice.exeption.ResourceNotFoundException;
import com.taher.ordersystemservice.dto.request.CustomerRequest;
import com.taher.ordersystemservice.dto.response.CustomerResponse;
import com.taher.ordersystemservice.entity.Customer;
import com.taher.ordersystemservice.mapper.CustomerMapper;
import com.taher.ordersystemservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest request) {

        Customer customer = Customer.builder()
                .fullName(request.fullName())
                .email(request.email())
                .phone(request.phone())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getFullName(),
                savedCustomer.getEmail(),
                savedCustomer.getPhone(),
                savedCustomer.getCreatedAt()
        );
    }


//    public CustomerResponse getCustomerById(Long id) {
//        return customerRepository.findById(id)
//                .map(customerMapper::mapToResponse)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id.toString()));
//    }

//    public List<CustomerResponse> getAllCustomers() {
//        return customerRepository.findAll().stream().map(customerMapper::mapToResponse).toList();
//    }
}
