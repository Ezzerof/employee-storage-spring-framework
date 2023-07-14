package com.office.employees_storage_spring_framework.repository;

import com.office.employees_storage_spring_framework.model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class EmployeeRepoStub implements EmployeeRepo {

    List<Employee> list = new ArrayList<>();

    @Override
    public <S extends Employee> S save(S entity) {
        list.add(entity);
        return entity;
    }

    @Override
    public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
        for (S iterable : entities)
            list.add(iterable);
        return (Iterable<S>) list.get(0);
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        Optional<Employee> optional = null;
        for (Employee e : list) {
            if (e.getEmpId() == aLong)
                optional = Optional.of(e);
        }
        return optional;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Employee> findAll() {
        return list;
    }

    @Override
    public Iterable<Employee> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return list.size();
    }

    @Override
    public void deleteById(Long aLong) {

        Iterator<Employee> iterator = list.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getEmpId() == aLong) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Employee> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
