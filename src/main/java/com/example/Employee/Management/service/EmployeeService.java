package com.example.Employee.Management.service;


import com.example.Employee.Management.model.Employee;
import com.example.Employee.Management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;


    // CRUD Operation Method's
    public Employee save(Employee emp){
        return repo.save(emp);

    }

    public List<Employee> getAll(){
        return repo.findAll();
    }

    public Employee getById(Long id){
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

}
