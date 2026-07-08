package com.example.Employee.Management.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private Double salary;
}