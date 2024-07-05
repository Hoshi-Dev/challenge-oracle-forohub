package com.alura.forohub.domain.user;

import com.alura.forohub.domain.Role;

public record UpdateUserDTO(
        String name,
        String email,
        String password,
        Role role,
        Boolean status
) {
}
