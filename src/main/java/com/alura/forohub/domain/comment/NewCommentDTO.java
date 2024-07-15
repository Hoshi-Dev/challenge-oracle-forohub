package com.alura.forohub.domain.comment;

import com.alura.forohub.domain.topic.Topic;
import com.alura.forohub.domain.user.User;
import jakarta.validation.constraints.NotNull;

public record NewCommentDTO(
        @NotNull(message = "El comentario no puede estar vacío.")
        String message,

        @NotNull(message = "El ID de Topic no puede estar vacío.")
        String topicId,

        @NotNull(message = "El ID de usuario no puede estar vacío.")
        String userId
) {
}
