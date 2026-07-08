package com.example.Employee.Management.repository;

import com.example.Employee.Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);


}
