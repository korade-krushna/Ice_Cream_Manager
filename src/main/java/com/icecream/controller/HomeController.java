package com.icecream.controller;

import com.icecream.dao.EmployeeRepository;
import com.icecream.dao.IceCreamRepositary;
import com.icecream.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
    @Autowired
    private IceCreamRepositary iceCreamRepositary;
    @Autowired
    private EmployeeRepository employeeRepository;
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
    @RequestMapping("/become-employee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "become-employee";
    }

    //Controller For Registering the Employee
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String do_register(@ModelAttribute("employee") Employee employee, Model model) {
        try {
            employee.setRoll("ROLE_EMPLOYEE");
            employee.setEnabled(true);
        } catch (Exception e) {

        }
        //this.employeeRepository.save(employee);
        return "become-employee";
    }
}
