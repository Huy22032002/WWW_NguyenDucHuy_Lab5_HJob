package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.Company;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.services.CandidateService;
import vn.edu.iuh.fit.services.CompanyService;
import vn.edu.iuh.fit.services.JobService;

import java.security.Principal;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CandidateService candidateService;
//    @GetMapping("/{id}")
//    public String getJobDetails(@PathVariable Long id, Model model, Principal principal) {
//        Job job = jobService.findJobById(id);
//        String username = principal.getName();
//        Candidate candidate = candidateService.findNameByUsername(username);
//        if (job == null) {
//            return "redirect:/home";
//        }
//        model.addAttribute("job", job);
//        model.addAttribute("candidate", candidate);
//        return "job-screen/detailJob";
//    }
}
