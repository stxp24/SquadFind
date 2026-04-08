package com.squadfind.backend.controller;

import com.squadfind.backend.dto.LoginUserRequest;
import com.squadfind.backend.dto.RegisterUserRequest;
import com.squadfind.backend.model.User;
import com.squadfind.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Long> registerUser(@RequestBody RegisterUserRequest request) {
        Long userId = userService.registerUser(request).getId();
        return ResponseEntity.ok(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginUserRequest request) {
        User loggedIn = userService.login(request);
        return ResponseEntity.ok(loggedIn);
    }
}
