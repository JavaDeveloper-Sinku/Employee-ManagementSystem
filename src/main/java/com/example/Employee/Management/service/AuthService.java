package com.example.Employee.Management.service;

import com.example.Employee.Management.dto.request.LoginRequest;
import com.example.Employee.Management.dto.request.RefreshTokenRequest;
import com.example.Employee.Management.dto.request.RegisterRequest;
import com.example.Employee.Management.dto.response.AuthResponse;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);

    void logout(String email);
}
