package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.Category;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        String title,
        String message,
        Category category,
        Boolean status
) {
}
