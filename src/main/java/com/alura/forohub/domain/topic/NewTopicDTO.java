package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.Category;
import jakarta.validation.constraints.NotNull;

public record NewTopicDTO(
        @NotNull(message = "El título no puede estar vacío.")
        String title,

        @NotNull(message = "El comentario no puede estar vacío.")
        String message,

        @NotNull(message = "La categoría del Topic no puede estar vacía.")
        Category category,

        @NotNull(message = "El nombre de usuario no puede estar vacío.")
        String user

) {
}
