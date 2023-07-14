package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.dto.EmployeeDTO;
import com.office.employees_storage_spring_framework.dto.PrivateEmployeeDTO;
import com.office.employees_storage_spring_framework.exception.EmployeeNotFoundException;
import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.repository.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeService implements AppService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee addEmployee(PrivateEmployeeDTO employee) {
        Employee convertedEmployee = privateEmployeeDtoToEmployee(employee);
        return employeeDAO.save(convertedEmployee);
    }

    public void deleteEmployee(Long id) {
        EmployeeDTO temployee = findEmployeeById(id);
        if (temployee != null)
            employeeDAO.deleteById(id);
        else
            throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    public List<EmployeeDTO> findAllEmployees() {
        return  employeeDAO.findAll()
                .stream()
                .map(employee -> employeeToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    public EmployeeDTO findEmployeeById(Long id) {
        Optional<Employee> optional = employeeDAO.findById(id);

        if (optional.isPresent())
            return employeeToEmployeeDTO(optional.get());

        throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    public Employee updateEmployee(PrivateEmployeeDTO employee) {
        Employee convertedEmployee = privateEmployeeDtoToEmployee(employee);
        return employeeDAO.save(convertedEmployee);
    }

    private EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .title(employee.getTitle())
                .firstName(employee.getFirstName())
                .secondName(employee.getSecondName())
                .age(employee.getAge())
                .picture(employee.getPicture())
                .email(employee.getEmail())
                .username(employee.getUsername()).build();

    }

    private Employee privateEmployeeDtoToEmployee(PrivateEmployeeDTO privateEmployeeDTO) {
        return Employee.builder()
                .title(privateEmployeeDTO.getTitle())
                .firstName(privateEmployeeDTO.getFirstName())
                .secondName(privateEmployeeDTO.getSecondName())
                .age(privateEmployeeDTO.getAge())
                .email(privateEmployeeDTO.getEmail())
                .phoneNumber(privateEmployeeDTO.getPhoneNumber())
                .username(privateEmployeeDTO.getUsername())
                .picture(privateEmployeeDTO.getPicture())
                .password(privateEmployeeDTO.getPassword())
                .address(privateEmployeeDTO.getAddress())
                .country(privateEmployeeDTO.getCountry())
                .town(privateEmployeeDTO.getTown())
                .build();
    }
}
