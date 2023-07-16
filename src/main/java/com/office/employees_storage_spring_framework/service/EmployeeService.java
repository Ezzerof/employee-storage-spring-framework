package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.dao.EmployeeDAO;
import com.office.employees_storage_spring_framework.dto.InputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.OutputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.UpdateEmployeeDTO;
import com.office.employees_storage_spring_framework.exception.EmployeeNotFoundException;
import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.utils.FetchApiResponse;
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

    @Override
    public Employee addEmployee(InputEmployeeDTO employee) {
        Employee convertedEmployee = employeeDtoToEmployee(employee);
        return employeeDAO.save(convertedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        OutputEmployeeDTO temployee = findEmployeeById(id);
        if (temployee != null)
            employeeDAO.deleteById(id);
        else
            throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    @Override
    public List<OutputEmployeeDTO> findAllEmployees() {
        return employeeDAO.findAll()
                .stream()
                .map(employee -> employeeToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    @Override
    public OutputEmployeeDTO findEmployeeById(Long id) {
        Optional<Employee> optional = employeeDAO.findById(id);

        if (optional.isPresent())
            return employeeToEmployeeDTO(optional.get());

        throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> optional = employeeDAO.findById(id);

        if (optional.isPresent())
            return optional.get();
        throw new EmployeeNotFoundException("Employee by id: " + id + " not found.");
    }

    @Override
    public Employee updateEmployee(Long id, UpdateEmployeeDTO updateEmployee) {
        Employee existedEmployee = getEmployeeById(id);
        return employeeDAO.save(updateEmployeeDTO(updateEmployee, existedEmployee));
    }

    @Override
    public Employee saveRandomEmployee() {
        Employee employee = addEmployee(FetchApiResponse.fetchBody());
        return employee;
    }


    private OutputEmployeeDTO employeeToEmployeeDTO(Employee employee) {
        return OutputEmployeeDTO.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .firstName(employee.getFirstName())
                .secondName(employee.getSecondName())
                .age(employee.getAge())
                .picture(employee.getPicture())
                .email(employee.getEmail())
                .username(employee.getUsername())
                .address(employee.getAddress())
                .town(employee.getTown())
                .country(employee.getCountry())
                .phoneNumber(employee.getPhoneNumber()).build();

    }

    private Employee updateEmployeeDTO(UpdateEmployeeDTO updateEmployeeDTO, Employee existingEmployee) {
        existingEmployee.setTitle(updateEmployeeDTO.getTitle());
        existingEmployee.setFirstName(updateEmployeeDTO.getFirstName());
        existingEmployee.setSecondName(updateEmployeeDTO.getSecondName());
        existingEmployee.setAge(updateEmployeeDTO.getAge());
        existingEmployee.setEmail(updateEmployeeDTO.getEmail());
        existingEmployee.setUsername(updateEmployeeDTO.getUsername());
        existingEmployee.setAddress(updateEmployeeDTO.getAddress());
        existingEmployee.setTown(updateEmployeeDTO.getTown());
        existingEmployee.setCountry(updateEmployeeDTO.getCountry());
        existingEmployee.setPhoneNumber(updateEmployeeDTO.getPhoneNumber());
        existingEmployee.setPicture(updateEmployeeDTO.getPicture());

        return existingEmployee;
    }

    private Employee employeeDtoToEmployee(InputEmployeeDTO privateEmployeeDTO) {
        Employee employee = new Employee(
                privateEmployeeDTO.getTitle(),
                privateEmployeeDTO.getFirstName(),
                privateEmployeeDTO.getSecondName(),
                privateEmployeeDTO.getAge(),
                privateEmployeeDTO.getEmail(),
                privateEmployeeDTO.getPhoneNumber(),
                privateEmployeeDTO.getAddress(),
                privateEmployeeDTO.getCountry(),
                privateEmployeeDTO.getTown(),
                privateEmployeeDTO.getUsername(),
                privateEmployeeDTO.getPassword(),
                privateEmployeeDTO.getPicture()
        );

        return employee;
    }

}
