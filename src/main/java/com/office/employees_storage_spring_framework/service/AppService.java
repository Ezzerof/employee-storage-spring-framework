package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.dto.EmployeeDTO;
import com.office.employees_storage_spring_framework.dto.PrivateEmployeeDTO;
import com.office.employees_storage_spring_framework.model.Employee;

import java.util.List;

public interface AppService {

    Employee addEmployee(PrivateEmployeeDTO employee);

    void deleteEmployee(Long id);

    List<EmployeeDTO> findAllEmployees();

    EmployeeDTO findEmployeeById(Long id);

    Employee updateEmployee(PrivateEmployeeDTO employee);

}
