package com.alura.forohub.domain.user;

import com.alura.forohub.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseUserDTO createNewUser(NewUserDTO data) {

        validateUserEmailNotDuplicated(data.email());
        validateUserNameNotDuplicated(data.name());

        var user = new User(data, passwordEncoder);
        userRepository.save(user);
        return createResponse(user);
    }

    public Page<ResponseUserDTO> getAllUsers(Pageable paged) {
        Page<User> users = userRepository.findAll(paged);
        return users.map(this::createResponse);
    }

    public ResponseUserDTO getUserById(Long id) {
        var searchedUser = validateUserById(id);
        return createResponse(searchedUser);
    }

    public ResponseUserDTO updateUserById(Long id, UpdateUserDTO data) {
        var userUpdated = validateUserById(id);
        userUpdated.setUpdatedAt(LocalDateTime.now());

        if (data.name() != null && !data.name().isEmpty()){
            validateUserNameNotDuplicated(data.name());
            userUpdated.setName(data.name());
        }
        if (data.email() != null && !data.email().isEmpty()){
            validateUserEmailNotDuplicated(data.email());
            userUpdated.setEmail(data.email());
        }
        if (data.password() != null && !data.password().isEmpty()){
            userUpdated.setPassword(data.password(), passwordEncoder);
        }
        if (data.role() != null && !data.role().toString().isEmpty()){
            userUpdated.setRole(data.role());
        }
        if (data.status() != null && !data.status().toString().isEmpty()){
            userUpdated.deactivateUser(data.status());
        }
        return createResponse(userUpdated);
    }

    public boolean deleteUserById(Long id) {
        var deletedUser = validateUserById(id);
        userRepository.delete(deletedUser);
        return true;
    }

    private void validateUserNameNotDuplicated(String name) {
        if (userRepository.existsByName(name)) {
            throw new IntegrityValidation("El nombre de usuario ya está en uso.");
        }
    }

    private void validateUserEmailNotDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IntegrityValidation("El correo electrónico ya está registrado.");
        }
    }

    private User validateUserById(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new ValidationException("No se encontró el usuario en la base de datos"));
    }

    private ResponseUserDTO createResponse(User user) {
        return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

}
