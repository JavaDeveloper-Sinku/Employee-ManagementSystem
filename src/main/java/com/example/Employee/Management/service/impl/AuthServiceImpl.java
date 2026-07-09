package com.example.Employee.Management.service.impl;

import com.example.Employee.Management.dto.request.LoginRequest;
import com.example.Employee.Management.dto.request.RegisterRequest;
import com.example.Employee.Management.entity.Role;
import com.example.Employee.Management.entity.User;
import com.example.Employee.Management.enums.RoleType;
import com.example.Employee.Management.exception.DuplicateResourceException;
import com.example.Employee.Management.repository.RoleRepository;
import com.example.Employee.Management.repository.UserRepository;
import com.example.Employee.Management.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Employee.Management.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
           throw new DuplicateResourceException("User already registered with this email");
        }

        RoleType roleType = RoleType.valueOf(request.getRole().toUpperCase());

        Role role = roleRepository.findByName(roleType).orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return jwtService.generateToken(request.getEmail());
    }
}
