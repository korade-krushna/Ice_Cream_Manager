package com.icecream.controller;

import com.icecream.dao.EmployeeRepository;
import com.icecream.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        String username = principal.getName();
        Employee employee = employeeRepository.getEmployeebyUsername(username);
        model.addAttribute("employee", employee);
        return "Employee/employee-dashboard";
    }
}
