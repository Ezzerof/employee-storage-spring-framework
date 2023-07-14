package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.exception.EmployeeNotFoundException;
import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.repository.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee addEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee temployee = findEmployeeById(id);
        if (temployee != null)
            employeeDAO.deleteById(id);
        else
            throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    public Iterable<Employee> findAllEmployees() {
        return employeeDAO.findAll();
    }

    public Employee findEmployeeById(Long id) {
        Optional optional = employeeDAO.findById(id);

        if (optional.isPresent())
            return (Employee) optional.get();

        throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    public Employee updateEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }
}
