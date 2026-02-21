package com.example.Employee.Management.service;


import com.example.Employee.Management.model.Employee;
import com.example.Employee.Management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository repository;

    @Async
    public void generateReport(){
        List<Employee> list = repository.findAll();
        double totalSalary = list.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println("Total Employees: "+ list.size());
        System.out.println("Total Salary: "+ totalSalary);
    }
}



// EnableAsync  Annotation  using and they are providing  @Async  annotation
// in this Annotation  tell this Spring boot  too handle Thread Pool and Excuter ( Start / run ) task for All Threads
