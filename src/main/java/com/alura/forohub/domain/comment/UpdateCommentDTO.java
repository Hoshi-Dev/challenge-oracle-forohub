package com.alura.forohub.domain.comment;

public record UpdateCommentDTO(
        String message,
        Boolean status
) {
}
