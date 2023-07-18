package com.office.employees_storage_spring_framework.service;

import com.office.employees_storage_spring_framework.dao.EmployeeDAO;
import com.office.employees_storage_spring_framework.dto.InputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.OutputEmployeeDTO;
import com.office.employees_storage_spring_framework.dto.UpdateEmployeeDTO;
import com.office.employees_storage_spring_framework.exception.EmployeeNotFoundException;
import com.office.employees_storage_spring_framework.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;
    @InjectMocks
    private EmployeeService service;

    @DisplayName("Test add employee to database")
    @Test
    void add_employee() {
        // Given
        InputEmployeeDTO mockEmployee = InputEmployeeDTO.builder()
                .id(0)
                .firstName("Ivan")
                .secondName("Bruno")
                .title("Mr")
                .age("21")
                .email("bruno@gmail.com")
                .address("21 Forest View")
                .town("London")
                .country("United Kingdom")
                .phoneNumber("1234567897")
                .picture("picture")
                .username("usernametest")
                .password("4123123")
                .build();

        Employee testEmployee = new Employee(
                "Mr",
                "John",
                "Somehow",
                "55",
                "j.somehow@gmail.com",
                "4989446897",
                "41 Altern Road",
                "London",
                "United Kingdom",
                "usernametest",
                "password",
                "picture"
        );

        // When
        when(employeeDAO.save(any(Employee.class))).thenReturn(testEmployee);

        Employee verifyEmployee = service.addEmployee(mockEmployee);

        // Then
        assertNotNull(verifyEmployee);
        assertEquals(testEmployee.getId(), verifyEmployee.getId());
        assertEquals(testEmployee.getFirstName(), verifyEmployee.getFirstName());
        assertEquals(testEmployee.getSecondName(), verifyEmployee.getSecondName());

    }

    @DisplayName("Delete an existent employee")
    @Test
    void delete_an_existent_employee() {
        // Given
        long testEmployeeId = 1L;

        OutputEmployeeDTO mockEmployee = OutputEmployeeDTO.builder()
                .id(1)
                .firstName("Ivan")
                .secondName("Bruno")
                .title("Mr")
                .age("21")
                .email("bruno@gmail.com")
                .address("21 Forest View")
                .town("London")
                .country("United Kingdom")
                .phoneNumber("1234567897")
                .picture("picture")
                .username("usernametest")
                .build();

        Employee testEmployee = new Employee(
                "Mr",
                "John",
                "Somehow",
                "55",
                "j.somehow@gmail.com",
                "4989446897",
                "41 Altern Road",
                "London",
                "United Kingdom",
                "usernametest",
                "password",
                "picture"
        );

        // Set up the mock behavior of findEmployeeById
        when(employeeDAO.findById(any())).thenReturn(Optional.of(testEmployee));

        // When
        assertDoesNotThrow(() -> service.deleteEmployee(testEmployeeId));

        // Then
        verify(employeeDAO, times(1)).deleteById(testEmployeeId);
    }

    @DisplayName("Delete non existing employee")
    @Test
    void delete_non_existent_employee() {
        // Given
        long testEmployeeId = 1L;

        // Set up the mock behavior of findEmployeeById to throw EmployeeNotFoundException
        when(employeeDAO.findById(any())).thenThrow(new EmployeeNotFoundException("Employee by id: " + testEmployeeId + " not found."));

        // When/Then
        assertThrows(EmployeeNotFoundException.class, () -> service.deleteEmployee(testEmployeeId));
        verify(employeeDAO, never()).deleteById(anyLong());
    }

    @DisplayName("Getting all employees from a database")
    @Test
    void find_all_employees_from_database() {
        // Given
        List<Employee> employeeList = Arrays.asList(
                new Employee("Mr",
                        "John",
                        "Somehow",
                        "23",
                        "j.somehow@gmail.com",
                        "49894",
                        "41 Altern Road",
                        "London",
                        "UK",
                        "username",
                        "pwass",
                        "picture"),
                new Employee("Mrs",
                        "Dan",
                        "Somehow",
                        "23",
                        "d.somehow@gmail.com",
                        "123124123",
                        "41 Altern Road",
                        "London",
                        "UK",
                        "usernamee",
                        "pass",
                        "picture")
        );

        // Set up the mock behavior of employeeDAO.findAll
        when(employeeDAO.findAll()).thenReturn(employeeList);

        // When
        List<OutputEmployeeDTO> result = service.findAllEmployees();

        // Then
        int expectedSize = 2;
        int actualSize = result.size();

        assertEquals(expectedSize, actualSize);
        assertNotNull(result);
        verify(employeeDAO, times(1)).findAll();
    }

    @DisplayName("Getting all employees from a empty database")
    @Test
    void get_all_employees_from_empty_database() {
        // Given
        List<Employee> employeeList = Collections.emptyList();

        // When
        when(employeeDAO.findAll()).thenReturn(employeeList);

        List<OutputEmployeeDTO> result = service.findAllEmployees();

        // Then
        int expectedSize = 0;
        int actualSize = result.size();

        assertEquals(expectedSize, actualSize);
        assertNotNull(result);
        verify(employeeDAO, times(1)).findAll();
    }

    @DisplayName("Getting an existent employee by id")
    @Test
    void find_employee_by_id() {
        // Given
        long testEmployeeId = 1L;
        Employee employee = new Employee(
                "Mr",
                "John",
                "Somehow",
                "23",
                "j.somehow@gmail.com",
                "49894",
                "41 Altern Road",
                "London",
                "UK",
                "username",
                "pass",
                "picture");

        employee.setId(testEmployeeId);
        // When
        when(employeeDAO.findById(testEmployeeId)).thenReturn(Optional.of(employee));
        // Then
        String expectedName = "John";
        String expectedEmail = "j.somehow@gmail.com";
        OutputEmployeeDTO testEmployee = service.findEmployeeById(testEmployeeId);
        String actualName = testEmployee.getFirstName();
        String actualEmail = testEmployee.getEmail();

        assertAll(
                () -> assertTrue(expectedName.equals(actualName)),
                () -> assertTrue(expectedEmail.equals(actualEmail))
        );
    }


}