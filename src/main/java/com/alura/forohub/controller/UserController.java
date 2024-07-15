package com.alura.forohub.controller;

import com.alura.forohub.domain.user.NewUserDTO;
import com.alura.forohub.domain.user.UpdateUserDTO;
import com.alura.forohub.domain.user.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid NewUserDTO data) {
        var newUser = userService.createNewUser(data);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<?> listAllUsers(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable paged) {
        var users = userService.getAllUsers(paged);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
        var user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateUserDTO data) {
        var userUpdated = userService.updateUserById(id, data);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long id) {
        if (userService.deleteUserById(id)) {
            return ResponseEntity.ok("User eliminado satisfactoriamente.");
        }
        return ResponseEntity.badRequest().build();
    }
}
