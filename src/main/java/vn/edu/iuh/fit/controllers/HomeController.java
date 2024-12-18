package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.fit.services.CompanyService;
import vn.edu.iuh.fit.services.JobService;

@Controller
public class HomeController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobService jobService;

    @GetMapping({"/home","/"})
    public String home(Model model) {
        model.addAttribute("lstJob", jobService.findAll());
        model.addAttribute("lstCompany", companyService.findAll()) ;
        return "default/home";
    }
    @GetMapping("/logout")
    public String logout() {
        return "default/login";
    }
}
