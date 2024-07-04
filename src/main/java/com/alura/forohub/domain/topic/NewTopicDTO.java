package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.Category;
import jakarta.validation.constraints.NotNull;

public record NewTopicDTO(
        @NotNull(message = "No puede haber título duplicados.")
        String title,

        @NotNull(message = "No puede haber mensajes duplicados.")
        String message,

        @NotNull(message = "Indique la categoría del topic.")
        Category category,

        @NotNull(message = "Ingrese su nombre de usuario.")
        String user

) {
}
