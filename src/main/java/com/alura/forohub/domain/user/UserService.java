package com.alura.forohub.domain.user;

import com.alura.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseUserDTO createNewUser(NewUserDTO data) {
        if (userRepository.existsByEmail(data.email())) {
            throw new IntegrityValidation("El correo electrónico ya está registrado.");
        }

        if (userRepository.existsByName(data.name())) {
            throw new IntegrityValidation("El nombre de usuario ya está en uso.");
        }

        var user = new User(data, passwordEncoder);
        userRepository.save(user);
        return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
