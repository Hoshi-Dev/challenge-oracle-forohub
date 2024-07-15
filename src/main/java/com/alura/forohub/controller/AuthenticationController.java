package com.alura.forohub.controller;

import com.alura.forohub.domain.user.AuthenticationUserDTO;
import com.alura.forohub.domain.user.User;
import com.alura.forohub.infra.security.JWTTokenDTO;
import com.alura.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    @Operation(summary = "Login de Usuario")
    public ResponseEntity<?> authenticationUser(@RequestBody @Valid AuthenticationUserDTO data){
        Authentication authToken = new UsernamePasswordAuthenticationToken(data.name(), data.password());
        var authUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTToken));
    }
}
