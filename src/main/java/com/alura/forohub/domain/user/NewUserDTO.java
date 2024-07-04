package com.alura.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NewUserDTO(

        @NotNull
        String name,

        @NotNull
        @Email
        String email,

        @NotEmpty
        @Pattern(regexp = "[a-z0-9-]+")
        String password,

        @NotNull
        String role

) {
}
