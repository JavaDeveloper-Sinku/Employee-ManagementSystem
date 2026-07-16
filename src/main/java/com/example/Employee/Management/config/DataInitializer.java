package com.example.Employee.Management.config;

import com.example.Employee.Management.entity.Role;
import com.example.Employee.Management.enums.RoleType;
import com.example.Employee.Management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        for (RoleType roleType : RoleType.values()) {

            if (roleRepository.findByName(roleType).isEmpty()) {

                Role role = Role.builder()
                        .name(roleType)
                        .build();

                roleRepository.save(role);
            }
        }

        System.out.println("Default roles initialized successfully.");
    }
}
