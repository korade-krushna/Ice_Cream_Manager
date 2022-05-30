package com.icecream.controller;

import com.icecream.dao.EmployeeRepository;
import com.icecream.helper.Message;
import com.icecream.models.Employee;
import com.icecream.models.IceCream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @ModelAttribute
    public void addData(Model model, Principal principal){
        String username = principal.getName();
        Employee employee = employeeRepository.getEmployeebyUsername(username);
        model.addAttribute("employee", employee);
    }
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "Employee/employee-dashboard";
    }
    @RequestMapping("/add-cream")
    public String addIceCream(Model model){
        model.addAttribute("icecream", new IceCream());
        return "Employee/add_ice_cream";
    }
    @PostMapping("/do_add")
    public String do_add(@ModelAttribute IceCream icecream,
                         Model model, @RequestParam("i-image")MultipartFile file, HttpSession session){
        try {
            System.out.println(file);
            if(!file.isEmpty()){
                icecream.setImage(file.getOriginalFilename());
                File saved_file = new ClassPathResource("static/img").getFile();
                Paths.get(saved_file.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), Paths.get(saved_file.getAbsolutePath()
                        + File.separator +
                        file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                session.setAttribute("message", new Message("Ice Cream Added Succesfully", "alert-success"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Sommething went wrong", "alert-danger"));
        }
        System.out.println(icecream);
        return "Employee/add_ice_cream";
    }
}
