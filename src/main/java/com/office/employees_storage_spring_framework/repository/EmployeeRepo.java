package com.office.employees_storage_spring_framework.repository;


import com.office.employees_storage_spring_framework.model.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 *  By using JpaRepository(Class, Type) we can use the CRUD methods from it
 */

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
