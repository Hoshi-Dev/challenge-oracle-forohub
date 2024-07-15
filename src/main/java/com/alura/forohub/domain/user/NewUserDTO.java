package com.alura.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NewUserDTO(

        @NotNull(message = "El nombre de usuario no puede estar vacío.")
        String name,

        @NotNull(message = "No puede haber comentario vacío.")
        @Email
        String email,

        @NotEmpty(message = "El password no puede estar vacío.")
        @Pattern(regexp = "[a-z0-9-]+")
        String password,

        @NotNull(message = "El rol del usuario no puede estar vacío.")
        String role

) {
}
