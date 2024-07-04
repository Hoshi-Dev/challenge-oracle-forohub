package com.alura.forohub.controller;

import com.alura.forohub.domain.user.NewUserDTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {





    @PostMapping
    public void registerUser(@RequestBody @Valid NewUserDTO data){
        System.out.println(data.password());


        System.out.println(data);
    }
}
