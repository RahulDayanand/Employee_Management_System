package com.capgemini.employeemanagement.repository;

import com.capgemini.employeemanagement.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DataJpaTest  //to test JPA Repositories
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void existsByEmailId() {
        String emailId = "zyx@gmail.com";
        String employeeFirstName = "zy";
        String employeeLastName = "x";
        Employee employee = new Employee(employeeFirstName,employeeLastName,
                emailId);
        employeeRepository.save(employee);

        //when

        boolean Expected = employeeRepository.existsByEmailId(emailId);

       //then

        assertThat(Expected).isTrue();
    }
    @Test
    void EmailIdNotExists(){
        //given

        String emailId = "zyx@gmail.com";
        //when

        boolean Expected = employeeRepository.existsByEmailId(emailId);

        //then

        assertThat(Expected).isFalse();
    }


}