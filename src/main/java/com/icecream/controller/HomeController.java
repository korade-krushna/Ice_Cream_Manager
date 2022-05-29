package com.icecream.controller;

import com.icecream.dao.EmployeeRepository;
import com.icecream.dao.IceCreamRepositary;
import com.icecream.helper.Message;
import com.icecream.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


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
    public String do_register(@ModelAttribute("employee") Employee employee,
                              Model model, HttpSession session) throws Exception {
        try {
            employee.setRoll("ROLE_EMPLOYEE");
            employee.setEnabled(true);
            this.employeeRepository.save(employee);
            model.addAttribute("employee", new Employee());
            session.setAttribute("message", new Message
                    ("Welcome aboard, it is a pleasure to have you join our team. Let us create magic.", "alert-success"));
            return "become-employee";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("employee", employee);
            session.setAttribute("message", new Message("email-id already exist",
                    "alert-danger"));
            return "become-employee";
        }
    }
}
