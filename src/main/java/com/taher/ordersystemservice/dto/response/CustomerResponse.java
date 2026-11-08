package com.taher.ordersystemservice.dto.response;


import java.time.Instant;


public record CustomerResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        Instant createdAt
) {
}
