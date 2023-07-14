package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.model.Employee;
import com.office.employees_storage_spring_framework.repository.EmployeeRepo;
import com.office.employees_storage_spring_framework.repository.EmployeeRepoStub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceTest {

    private static EmployeeService employeeService;

    private static EmployeeRepo repo;
    private static Employee firstEmployee;

    @BeforeAll
    public static void setUp() {
        repo = new EmployeeRepoStub();
        employeeService = new EmployeeService(repo);
        firstEmployee = employeeService.addEmployee(new Employee("Andrew", "Flower", "a.flower@gmail.com", "59876514", "13 Highway", "New York"));
        firstEmployee.setEmpId(1);
    }

    @DisplayName("Test add employee to database")
    @Test
    void addEmployee() {
        // Given
        //Database has 1 employee

        // When
        Employee verifyEmployee = employeeService.addEmployee(new Employee("Dan", "Flower", "d.flower@gmail.com", "59876514", "13 Highway", "New York"));
        verifyEmployee.setEmpId(2);

        // Then
        assertEquals(2, verifyEmployee.getEmpId());
        assertEquals("d.flower@gmail.com", verifyEmployee.getEmail());
    }

    @Test
    void deleteEmployee() {
        // Given
        long empId = firstEmployee.getEmpId();

        // When
        employeeService.deleteEmployee(empId);

        // Then
        assertEquals(0, repo.count());
    }

    @Test
    void findAllEmployees() {
        // Given
        employeeService.addEmployee(new Employee("Dan", "Flower", "d.flower@gmail.com", "59876514", "13 Highway", "New York"));
        employeeService.addEmployee(new Employee("C", "Flower", "c.flower@gmail.com", "59876514", "13 Highway", "New York"));
        employeeService.addEmployee(new Employee("B", "Flower", "b.flower@gmail.com", "59876514", "13 Highway", "New York"));


        // When
        Iterable<Employee> employeeList = employeeService.findAllEmployees();
        int actualSize = 0;
        for (Employee e : employeeList)
            actualSize++;

        // Then
        int expectedSize = 4;
        assertEquals(actualSize, expectedSize);
    }

    @Test
    void findEmployeeById() {
        // Given
        long empId = firstEmployee.getEmpId();

        // When
        Employee employee = employeeService.findEmployeeById(empId);

        // Then
        String expectedName = "Andrew";
        String expectedEmail = "a.flower@gmail.com";
        String actualName = employee.getFirstName();
        String actualEmail = employee.getEmail();

        assertAll(
                () -> assertTrue(expectedName.equals(actualName)),
                () -> assertTrue(expectedEmail.equals(actualEmail))
        );
    }

    @Test
    void updateEmployee() {
        // Given
        Employee newEmployee = new Employee("John", "Somehow", "j.somehow@gmail.com", "49894", "41 Altern Road", "London");
        newEmployee.setEmpId(9);

        // When
        employeeService.updateEmployee(newEmployee);

        //Then
        String expectedName = "John";
        String expectedEmail = "j.somehow@gmail.com";
        String actualName = newEmployee.getFirstName();
        String actualEmail = newEmployee.getEmail();
        assertAll(
                () -> assertTrue(expectedName.equals(actualName)),
                () -> assertTrue(expectedEmail.equals(actualEmail))
        );
    }
}