package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.exception.EmployeeNotFoundException;
import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public Iterable<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(Long id) {
        Optional optional = employeeRepo.findById(id);

        if (optional.isPresent())
            return (Employee) optional.get();

        throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
}
