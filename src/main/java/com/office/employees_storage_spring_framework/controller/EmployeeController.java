package com.office.employees_storage_spring_framework.controller;

import com.office.employees_storage_spring_framework.dto.OutputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.InputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.UpdateEmployeeDTO;
import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.service.EmployeeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<OutputEmployeeDTO>> getAllEmployees() {
        Iterable<OutputEmployeeDTO> iterable = service.findAllEmployees();
        List<OutputEmployeeDTO> allEmployees = StreamSupport.stream(iterable.spliterator(), false)
                .toList();

        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OutputEmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        OutputEmployeeDTO employee = service.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody InputEmployeeDTO employee) {
        Employee tempEmployee = service.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempEmployee);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody UpdateEmployeeDTO employee, @PathVariable("id") Long id) {
        Employee tempEmployee = service.updateEmployee(id, employee);
        return new ResponseEntity<>(tempEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id) {
        service.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
