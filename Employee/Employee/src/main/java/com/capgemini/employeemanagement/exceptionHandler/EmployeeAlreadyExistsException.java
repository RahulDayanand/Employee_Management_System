package com.capgemini.employeemanagement.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(String s){
        super(s);
    }
}
