package com.example.Employee.Management.service;


import com.example.Employee.Management.exception.EmployeeNotFoundException;
import com.example.Employee.Management.model.Employee;
import com.example.Employee.Management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {



    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }


    // CRUD Operation Method's
    @Transactional
    public Employee save(Employee emp){
        return repo.save(emp);

    }

    public Employee update(long id, Employee employee){

        Employee existingEmployee = getById(id);

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());

        return repo.save(existingEmployee);

    }

    public List<Employee> getAll(){
        return repo.findAll();
    }

    public Employee getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + id));
    }

    public void delete(Long id){

        Employee employee = getById(id);

        repo.delete(employee);
    }

}
