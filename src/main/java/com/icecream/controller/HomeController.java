package com.icecream.controller;

import com.icecream.dao.EmployeeRepository;
import com.icecream.dao.IceCreamRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private IceCreamRepositary iceCreamRepositary;
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("IceCreamList", iceCreamRepositary.findAll());
        return "home";
    }
}
