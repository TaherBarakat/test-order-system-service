package com.taher.ordersystemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrderSystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSystemServiceApplication.class, args);
    }

}
