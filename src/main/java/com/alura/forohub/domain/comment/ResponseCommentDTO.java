package com.alura.forohub.domain.comment;

import com.alura.forohub.domain.topic.Topic;

import java.time.LocalDateTime;

public record ResponseCommentDTO(
        Long id,
        String message,
        Long topicId,
        String topicTitle,
        String username,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean status
) {
}
