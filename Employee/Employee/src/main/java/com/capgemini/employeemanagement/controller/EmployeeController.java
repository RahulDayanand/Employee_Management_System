package com.capgemini.employeemanagement.controller;

import com.capgemini.employeemanagement.exceptionHandler.EmployeeNotFoundException;
import com.capgemini.employeemanagement.model.Employee;
import com.capgemini.employeemanagement.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl empimp;

    @GetMapping("/test")
    public String test() {
        Employee emp = new Employee();
        emp.setEmployeeId(1);
        emp.setEmployeeFirstName("Rahul");
        emp.setEmployeeLastName("D");
        emp.setEmailId("rahul@gmail.com");
        empimp.addEmployee(emp);
        return "all-employees";
    }

    @GetMapping("/")
    public String getEmployee(Model model) {

        return findPaginated(model, 1);

    }

    @GetMapping("/add")
    public String AddEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/addEmployee")
    public String createEmployee(Employee employee) {
        empimp.addEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) throws EmployeeNotFoundException {
        model.addAttribute("employee", empimp.findEmployeeById(id));
        return "edit-employee";

    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(Employee employee) {
        empimp.updateEmployee(employee);
        return "redirect:/";

    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        empimp.findEmployeeById(id);
        empimp.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(Model model, @PathVariable("pageNo") int pageNo) {
        int pageSize = 5;
        Page<Employee> page = empimp.findPaginated(pageNo, pageSize);
        List<Employee> listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("employees", listEmployees);
        return "all-employees";
    }


}