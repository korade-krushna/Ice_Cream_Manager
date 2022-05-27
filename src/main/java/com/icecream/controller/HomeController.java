package com.icecream.controller;

import com.icecream.dao.UserRepositary;
import com.icecream.models.Employee;
import com.icecream.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private UserRepositary userRepositary;

    @GetMapping("/test")
    @ResponseBody
    public Employee test() {
        Employee employee = new Employee();
        employee.setName("Chirag pandkar");
        employee.setEmail("chirag@gmail.com");

        return employee;
    }
}
