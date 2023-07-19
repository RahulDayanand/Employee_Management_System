package com.capgemini.employeemanagement.service;

import com.capgemini.employeemanagement.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {




    List<Employee> getEmployee();
    Employee findEmployeeById(int id);
    public Employee addEmployee(Employee emp);



    public void deleteEmployee(int id);
    Employee updateEmployee(Employee employee);
    Page<Employee> findPaginated(int pageNo,int pageSize);
}
