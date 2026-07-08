package com.example.Employee.Management.service.impl;

import com.example.Employee.Management.dto.request.EmployeeRequest;
import com.example.Employee.Management.dto.response.EmployeeResponse;
import com.example.Employee.Management.entity.Employee;
import com.example.Employee.Management.repository.EmployeeRepository;


import com.example.Employee.Management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;



    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {


        if(employeeRepository.existsByEmail(request.getEmail())){

            throw new RuntimeException(
                    "Employee already exists with this email"
            );
        }


        Employee employee = Employee.builder()

                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .department(request.getDepartment())
                .salary(request.getSalary())

                .build();


        Employee savedEmployee =
                employeeRepository.save(employee);


        return mapToResponse(savedEmployee);
    }



    @Override
    public List<EmployeeResponse> getAllEmployees() {


        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }



    @Override
    public EmployeeResponse getEmployeeById(Long id) {


        Employee employee =
                employeeRepository.findById(id)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found"
                                )
                        );


        return mapToResponse(employee);
    }




    @Override
    public EmployeeResponse updateEmployee(
            Long id,
            EmployeeRequest request
    ) {


        Employee employee =
                employeeRepository.findById(id)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found"
                                )
                        );


        employee.setFirstName(
                request.getFirstName()
        );

        employee.setLastName(
                request.getLastName()
        );

        employee.setEmail(
                request.getEmail()
        );

        employee.setPhone(
                request.getPhone()
        );

        employee.setDepartment(
                request.getDepartment()
        );

        employee.setSalary(
                request.getSalary()
        );


        Employee updatedEmployee =
                employeeRepository.save(employee);


        return mapToResponse(updatedEmployee);

    }




    @Override
    public void deleteEmployee(Long id) {


        Employee employee =
                employeeRepository.findById(id)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found"
                                )
                        );


        employeeRepository.delete(employee);

    }





    private EmployeeResponse mapToResponse(
            Employee employee
    ){


        return EmployeeResponse.builder()

                .id(employee.getId())

                .firstName(employee.getFirstName())

                .lastName(employee.getLastName())

                .email(employee.getEmail())

                .phone(employee.getPhone())

                .department(employee.getDepartment())

                .salary(employee.getSalary())

                .build();
    }

}
