package com.icecream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "employee-dashboard";
    }
}
