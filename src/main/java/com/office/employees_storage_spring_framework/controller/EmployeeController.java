package com.office.employees_storage_spring_framework.controller;

import com.office.employees_storage_spring_framework.dto.EmployeeDTO;
import com.office.employees_storage_spring_framework.dto.PrivateEmployeeDTO;
import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        Iterable<EmployeeDTO> iterable = service.findAllEmployees();
        List<EmployeeDTO> allEmployees = StreamSupport.stream(iterable.spliterator(), false)
                .toList();

        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeDTO employee = service.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody PrivateEmployeeDTO employee) {
        Employee tempEmployee = service.addEmployee(employee);
        return new ResponseEntity<>(tempEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody PrivateEmployeeDTO employee) {
        Employee tempEmployee = service.updateEmployee(employee);
        return new ResponseEntity<>(tempEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id) {
        service.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
