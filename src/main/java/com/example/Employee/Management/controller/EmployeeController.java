package com.example.Employee.Management.controller;

import com.example.Employee.Management.dto.request.EmployeeRequest;
import com.example.Employee.Management.dto.response.EmployeeResponse;
import com.example.Employee.Management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;



    // Create Employee
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
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
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(

            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "5")
            int size,
            @RequestParam(defaultValue = "id")
            String sortBy,
            @RequestParam(defaultValue = "asc")
            String sortDir
    ){

        return ResponseEntity.ok(
                employeeService.getAllEmployees(
                        page,
                        size,
                        sortBy,
                        sortDir
                )
        );
    }


    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<Page<EmployeeResponse>> searchEmployees(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String sortDir
    ) {

        return ResponseEntity.ok(
                employeeService.searchEmployees(
                        keyword,
                        page,
                        size,
                        sortBy,
                        sortDir
                )
        );
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<Page<EmployeeResponse>> filterEmployees(

            @RequestParam(required = false)
            String department,
            @RequestParam(required = false)
            Double minSalary,
            @RequestParam(required = false)
            Double maxSalary,
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "5")
            int size,
            @RequestParam(defaultValue = "id")
            String sortBy,
            @RequestParam(defaultValue = "asc")
            String sortDir
    ) {

        return ResponseEntity.ok(
                employeeService.filterEmployees(
                        department,
                        minSalary,
                        maxSalary,
                        page,
                        size,
                        sortBy,
                        sortDir
                )
        );
    }



    // Get Employee By Id
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR','EMPLOYEE')")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }




    // Update Employee
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid
            @RequestBody EmployeeRequest request
    ){

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }




    // Delete Employee
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Long id
    ){

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                "Employee deleted successfully"
        );
    }

}
