package com.example.Employee.Management.service;


import com.example.Employee.Management.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportThread extends Thread{

    private final EmployeeService service;

    public ReportThread(EmployeeService service){
        this.service = service;

    }

    public void run(){
        List<Employee> list = service.getAll();
        double totalSalary = list.stream().mapToDouble(Employee :: getSalary).sum();
        System.out.println("Total Employees: " + list.size());
        System.out.println("Total Salary: " + totalSalary);

    }

}


// Basic Way we can you Thread ( workers )   with any spring boot feature's  use