package com.taher.ordersystemservice.service;

import com.taher.ordersystemservice.dto.request.CustomerRequest;
import com.taher.ordersystemservice.dto.response.CustomerResponse;
import com.taher.ordersystemservice.entity.Customer;
import com.taher.ordersystemservice.mapper.CustomerMapper;
import com.taher.ordersystemservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void createCustomer_shouldReturnCustomerResponse() {

        // Arrange
        CustomerRequest request = new CustomerRequest(
                "John Doe",
                "john@example.com",
                "123456789"
        );

        Customer savedCustomer = Customer.builder()
                .id(1L)
                .fullName("John Doe")
                .email("john@example.com")
                .phone("123456789")
                .build();

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(savedCustomer);

        // Act
        CustomerResponse response = customerService.createCustomer(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("John Doe", response.fullName());
        assertEquals("john@example.com", response.email());
        assertEquals("123456789", response.phone());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
