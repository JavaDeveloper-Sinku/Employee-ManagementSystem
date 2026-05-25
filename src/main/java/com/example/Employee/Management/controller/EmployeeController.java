package com.example.Employee.Management.controller;


import com.example.Employee.Management.model.Employee;
import com.example.Employee.Management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee create(@RequestBody Employee emp){
        return employeeService.save(emp);
    }

    @GetMapping
    public List<Employee> getAll(){
        return  employeeService.getAll();
    }

    @GetMapping(value = "/{id}", version = "1.0")
    public Employee getById(@PathVariable Long id){
        return employeeService.getById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee emp){
        Employee existing = employeeService.getById(id);

        existing.setName(emp.getName());
        existing.setEmail(emp.getEmail());
        existing.setSalary(emp.getSalary());

        return employeeService.save(existing);

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        employeeService.delete(id);
        return "Deleted";
    }


}
