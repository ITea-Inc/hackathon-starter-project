package com.hackathon.springcrudjwtstarterproject.controller;

import com.hackathon.springcrudjwtstarterproject.dto.request.LoginUserRequest;
import com.hackathon.springcrudjwtstarterproject.dto.request.RegisterUserRequest;
import com.hackathon.springcrudjwtstarterproject.dto.response.AuthResponse;
import com.hackathon.springcrudjwtstarterproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerUserRequest));
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        return ResponseEntity.ok(authService.login(loginUserRequest));
    }
}
