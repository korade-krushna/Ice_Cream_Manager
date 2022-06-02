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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/employee") // Controller for all the api's in the /employee URLS's
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
    @RequestMapping("/dashboard") // controller for dashboard
    public String dashboard(Model model){
        List<IceCream> icecreams = this.iceCreamRepositary.findAll();
        model.addAttribute("icecreams", icecreams);
        return "Employee/employee-dashboard";
    }
    @RequestMapping("/add-cream") // controller for adding ice cream to dashboard
    public String addIceCream(Model model){
        model.addAttribute("icecream", new IceCream());
        return "Employee/add_ice_cream";
    }
    @PostMapping("/do_add") // controller for doing server side logic for adding ice cream
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
            } else {
                throw new Exception("Please Upload an Image");
            }
            this.iceCreamRepositary.save(icecream);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("icecream", icecream);
            session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
        }
        return "Employee/add_ice_cream";
    }

    @GetMapping("/delet-icecream/{id}") // controller for deleting ice cream
    public String deletIceCream(@PathVariable("id") Integer id, Model model){
        IceCream iceCream = this.iceCreamRepositary.findById(id).get();
        this.iceCreamRepositary.delete(iceCream);
        return "redirect:/employee/dashboard";
    }
    @RequestMapping("/update-icecream/{id}") // controller for updating ice cream
    public String updateIceCream(@PathVariable("id") Integer id, Model model){
        IceCream icecream = this.iceCreamRepositary.findById(id).get();
        model.addAttribute("icecream", icecream);
        return "/Employee/update-form";
    }
    @PostMapping("/process_icecream") // controller for doing server side logic for updating ice cream
    public String update(@ModelAttribute IceCream iceCream,
                         @RequestParam("i-image")MultipartFile file,
                         HttpSession session) throws Exception {
        try {
            IceCream exist = this.iceCreamRepositary.getIceCreambyName(iceCream.getName());

            if (exist != null) {
                throw new Exception("Ice cream already exist");
            }
            IceCream old = this.iceCreamRepositary.findById(iceCream.getIid()).get();
            if (!file.isEmpty()){
                File saved_file = new ClassPathResource("static/img").getFile();
                Paths.get(saved_file.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), Paths.get(saved_file.getAbsolutePath()
                        + File.separator +
                        file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                iceCream.setImage(file.getOriginalFilename());

            } else {
                iceCream.setImage(old.getImage());
            }
            this.iceCreamRepositary.save(iceCream);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "redirect:/employee/dashboard";
    }
}
