package com.capgemini.employeemanagement.repository;

import com.capgemini.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    boolean existsByEmailId(String emailId);
}






