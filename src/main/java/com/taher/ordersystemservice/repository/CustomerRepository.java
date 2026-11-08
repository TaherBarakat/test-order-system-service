package com.taher.ordersystemservice.repository;

import com.taher.ordersystemservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
