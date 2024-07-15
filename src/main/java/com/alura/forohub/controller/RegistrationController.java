package com.alura.forohub.controller;

import com.alura.forohub.domain.user.NewUserDTO;
import com.alura.forohub.domain.user.ResponseUserDTO;
import com.alura.forohub.domain.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/signup")
public class RegistrationController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Registra un nuevo usuario.")
    public ResponseEntity<?> userRegistration(@RequestBody @Valid NewUserDTO data, UriComponentsBuilder uriComponentsBuilder){
        try{
            ResponseUserDTO user = userService.createNewUser(data);

            URI url = uriComponentsBuilder.path("user/{id}").buildAndExpand(user.id()).toUri();
            return ResponseEntity.created(url).body(user);
        }catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().body("Validación fallida: " + e.getMessage());
        }
    }
}
