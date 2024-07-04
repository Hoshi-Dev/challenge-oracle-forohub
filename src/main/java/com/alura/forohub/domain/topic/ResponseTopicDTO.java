package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.Category;

import java.time.LocalDateTime;

public record ResponseTopicDTO(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String category,
        String user,
        Boolean status
) {
}
