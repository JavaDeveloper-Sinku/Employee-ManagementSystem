package com.example.Employee.Management.controller;

import com.example.Employee.Management.dto.request.LoginRequest;
import com.example.Employee.Management.dto.request.RegisterRequest;
import com.example.Employee.Management.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> register(
            @Valid
            @RequestBody RegisterRequest request
    ){

        return ResponseEntity.ok(
                authService.register(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid
            @RequestBody LoginRequest request
    ){

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}