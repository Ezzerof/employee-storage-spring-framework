package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.dto.InputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.OutputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.UpdateEmployeeDTO;
import com.office.employees_storage_spring_framework.model.Employee;

import java.util.List;

public interface AppService {

    Employee addEmployee(InputEmployeeDTO employee);

    void deleteEmployee(Long id);

    List<OutputEmployeeDTO> findAllEmployees();

    OutputEmployeeDTO findEmployeeById(Long id);

    Employee updateEmployee(Long id, UpdateEmployeeDTO employee);
    Employee saveRandomEmployee();

}
