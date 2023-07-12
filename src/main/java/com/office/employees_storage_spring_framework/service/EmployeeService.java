package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeService {

    @Autowired
    StudentDAO studentDAO;
    
}
