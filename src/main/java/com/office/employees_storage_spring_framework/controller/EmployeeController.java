package com.office.employees_storage_spring_framework.controller;

import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        Iterable<Employee> iterable = service.findAllEmployees();
        List<Employee> allEmployees = StreamSupport.stream(iterable.spliterator(), false)
                .toList();
        System.out.println(allEmployees);

        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam Long id) {
        Employee employee = service.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee tempEmployee = service.addEmployee(employee);
        return new ResponseEntity<>(tempEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee tempEmployee = service.updateEmployee(employee);
        return new ResponseEntity<>(tempEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> updateEmployee(@RequestParam Long id) {
        service.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
