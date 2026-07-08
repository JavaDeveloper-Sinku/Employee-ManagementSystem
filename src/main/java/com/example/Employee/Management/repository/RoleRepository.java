package com.example.Employee.Management.repository;

import com.example.Employee.Management.entity.Role;
import com.example.Employee.Management.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleType name);
}
