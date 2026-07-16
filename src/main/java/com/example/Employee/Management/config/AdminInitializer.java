package com.example.Employee.Management.config;

import com.example.Employee.Management.entity.Role;
import com.example.Employee.Management.entity.User;
import com.example.Employee.Management.enums.RoleType;
import com.example.Employee.Management.repository.RoleRepository;
import com.example.Employee.Management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {


        if (userRepository.existsByEmail("admin@gmail.com")) {
            return;
        }


        Role adminRole = roleRepository.findByName(RoleType.ADMIN)
                .orElseThrow(() ->
                        new RuntimeException("ADMIN role not found")
                );


        User admin = User.builder()
                .username("Administrator")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("Admin@123"))
                .enabled(true)
                .role(adminRole)
                .build();


        userRepository.save(admin);

        System.out.println("Default Admin Created Successfully.");
    }
}