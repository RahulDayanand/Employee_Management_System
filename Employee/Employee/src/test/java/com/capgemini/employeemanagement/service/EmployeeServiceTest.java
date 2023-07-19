package com.capgemini.employeemanagement.service;

import com.capgemini.employeemanagement.exceptionHandler.EmployeeAlreadyExistsException;
import com.capgemini.employeemanagement.exceptionHandler.EmployeeNotFoundException;
import com.capgemini.employeemanagement.model.Employee;
import com.capgemini.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks   //creates an instance of the class and injects the mocks
    private EmployeeServiceImpl employeeService;
    @Mock  //mocks the repo
    EmployeeRepository employeeRepository;

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeFirstName("Rahul");
        employee.setEmployeeLastName("D");
        employee.setEmailId("Rahul@gmail.com");

        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepository.findById(1)).thenReturn(optionalEmployee);
        Employee MyEmployee = employeeService.findEmployeeById(1);
        assertEquals("Rahul", MyEmployee.getEmployeeFirstName()); //used to check actual and expected value

    }

    @Test
    void testGetEmployeeByIdWithException() {

        when(employeeRepository.findById(1)).thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeById(1));

    }

    @Test
    void testGetEmployee() {

        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setEmployeeFirstName("Rahul");
        employee1.setEmployeeLastName("D");
        employee1.setEmailId("Rahul@gmail.com");
        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setEmployeeFirstName("Rishabh");
        employee2.setEmployeeLastName("Shetty");
        employee2.setEmailId("Rishabh@gmail.com");
        Employee employee3 = new Employee();
        employee2.setEmployeeId(3);
        employee2.setEmployeeFirstName("Tom");
        employee2.setEmployeeLastName("Holland");
        employee2.setEmailId("Tom@gmail.com");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> employees = employeeService.getEmployee();
        assertEquals(3, employees.size());

    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeFirstName("Rahul");
        employee.setEmployeeLastName("D");
        employee.setEmailId("Rahul@gmail.com");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee myEmployee = employeeService.addEmployee(employee);

        assertEquals("Rahul", myEmployee.getEmployeeFirstName());
        verify(employeeRepository, times(1)).save(employee); //Useful for testing void methods

    }

    @Test
    void testAddEmployeeWithException() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeFirstName("Rahul");
        employee.setEmployeeLastName("D");
        employee.setEmailId("Rahul@gmail.com");


        when(employeeRepository.existsByEmailId(employee.getEmailId())).thenThrow(EmployeeAlreadyExistsException.class);
        assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.addEmployee(employee));

    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeFirstName("Rahul");
        employee.setEmployeeLastName("D");
        employee.setEmailId("Rahul@gmail.com");

        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepository.findById(1)).thenReturn(optionalEmployee);

        Employee myEmployee = employeeService.findEmployeeById(1);

        employeeService.deleteEmployee(1);

        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }

}
