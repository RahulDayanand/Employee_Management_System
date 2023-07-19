package com.capgemini.employeemanagement.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ModelAndView handleEmployeeNotFoundException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("Employee-Not-Found");
        modelAndView.addObject("message", e.getLocalizedMessage());
        return modelAndView;
    }
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ModelAndView handleEmployeeAlreadyExistsException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("Employee-Already-Exist");
        modelAndView.addObject("message", e.getLocalizedMessage());
        return modelAndView;
    }
}
