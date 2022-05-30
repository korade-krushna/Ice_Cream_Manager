package com.icecream.controller;

import com.icecream.dao.EmployeeRepository;
import com.icecream.dao.IceCreamRepositary;
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
import java.util.List;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private IceCreamRepositary iceCreamRepositary;

    @ModelAttribute
    public void addData(Model model, Principal principal){
        String username = principal.getName();
        Employee employee = employeeRepository.getEmployeebyUsername(username);
        model.addAttribute("employee", employee);
    }
    @RequestMapping("/dashboard")
    public String dashboard(Model model){
        List<IceCream> icecreams = this.iceCreamRepositary.findAll();
        System.out.println(icecreams);
        model.addAttribute("icecreams", icecreams);
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
            if(!file.isEmpty()){
                icecream.setImage(file.getOriginalFilename());
                File saved_file = new ClassPathResource("static/img").getFile();
                Paths.get(saved_file.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), Paths.get(saved_file.getAbsolutePath()
                        + File.separator +
                        file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                session.setAttribute("message", new Message("Ice Cream Added Succesfully", "alert-success"));
                this.iceCreamRepositary.save(icecream);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("icecream", icecream);
            session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
        }
        return "Employee/add_ice_cream";
    }
}
