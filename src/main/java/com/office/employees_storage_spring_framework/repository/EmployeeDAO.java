package com.office.employees_storage_spring_framework.repository;


import com.office.employees_storage_spring_framework.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * By using JpaRepository(Class, Type) we can use the CRUD methods from it
 */

public interface EmployeeDAO extends JpaRepository<Employee, Long> {
}
