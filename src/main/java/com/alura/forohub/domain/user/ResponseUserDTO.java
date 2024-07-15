package com.alura.forohub.domain.user;

import java.time.LocalDateTime;

public record ResponseUserDTO(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
