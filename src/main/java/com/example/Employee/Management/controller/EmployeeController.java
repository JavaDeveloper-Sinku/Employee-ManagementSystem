package com.example.Employee.Management.controller;

import com.example.Employee.Management.dto.request.EmployeeRequest;
import com.example.Employee.Management.dto.response.EmployeeResponse;
import com.example.Employee.Management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;



    // Create Employee
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid
            @RequestBody EmployeeRequest request
    ){

        return new ResponseEntity<>(
                employeeService.createEmployee(request),
                HttpStatus.CREATED
        );
    }



    // Get All Employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){

        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }



    // Get Employee By Id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }




    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request
    ){

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }




    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Long id
    ){

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                "Employee deleted successfully"
        );
    }

}
