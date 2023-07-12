package com.office.employees_storage_spring_framework.repository;

import com.office.employees_storage_spring_framework.model.Employee;

public interface StudentDAO {

    void save(Employee employee);
    void remove(int id);
    void modify(int id);
    void read(int id);

}
