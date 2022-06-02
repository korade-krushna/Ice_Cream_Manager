package com.icecream.controller;

import com.icecream.dao.EmployeeRepository;
import com.icecream.dao.IceCreamRepositary;
import com.icecream.helper.Message;
import com.icecream.models.Employee;
import com.icecream.models.IceCream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private IceCreamRepositary iceCreamRepositary;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }
    @RequestMapping("/become-employee") //Controller For Registering the Employee
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "/Anybody/become-employee";
    }
    //Controller For Registering the Employee
    @RequestMapping(value = "/do_register", method = RequestMethod.POST) // controller for doing server side logic for adding the employee
    public String do_register(@ModelAttribute("employee") Employee employee,
                              Model model, HttpSession session) throws Exception {
        try {
            employee.setRoll("ROLE_EMPLOYEE");
            employee.setEnabled(true);
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            this.employeeRepository.save(employee);
            model.addAttribute("employee", new Employee());
            session.setAttribute("message", new Message
                    ("Welcome aboard, it is a pleasure to have you join our team. Let us create magic.", "alert-success"));
            return "redirect:/sign-in";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("employee", employee);
            session.setAttribute("message", new Message("email-id already exist",
                    "alert-danger"));
            return "/Anybody/become-employee";
        }
    }
    @GetMapping("/sign-in")  // controller for showing the login page
    public String customLogin(Model model) {
        return "/Anybody/login";
    }
    @RequestMapping("/availables") // controller for showing availables
    public String dashboard(Model model){
        List<IceCream> icecreams = this.iceCreamRepositary.findAll();
        model.addAttribute("icecreams", icecreams);
        return "/Anybody/available_icecream";
    }

}
