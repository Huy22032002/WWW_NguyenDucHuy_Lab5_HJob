package vn.edu.iuh.fit.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.models.*;
import vn.edu.iuh.fit.services.CandidateService;
import vn.edu.iuh.fit.services.JobApplicationService;
import vn.edu.iuh.fit.services.JobService;
import vn.edu.iuh.fit.services.SkillService;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller()
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobService jobService;
    @Autowired
    private JobApplicationService jobApplicationService;
    @Autowired
    private SkillService skillService;
    @GetMapping("/home")
    public String home(Model model, Principal principal) {

        String username = principal.getName();
        Candidate candidate = candidateService.findNameByUsername(username);
        List<Job> lstJob = jobService.recommendJobsForCandidate(candidate.getId());
        model.addAttribute("candidate", candidate);
        model.addAttribute("lstJob", lstJob);
        return "candidate-screen/home";
    }
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        Candidate candidate = candidateService.findNameByUsername(username);
        List<CandidateSkill> lstCanSkill = candidateService.getAllSkillsOfCan(candidate.getId());
        List<Experience> lstExp = experienceService.getByCandidate(candidate.getId());
        model.addAttribute("lstExp", lstExp);
        model.addAttribute("candidate", candidate);
        model.addAttribute("lstCanSkill", lstCanSkill);
        return "candidate-screen/profile";

    }

    @GetMapping("/suggestedSkillForCan/{candidateId}")
    public String suggestSkills(@PathVariable Long candidateId, Model model, Principal principal) {
        String username = principal.getName();
        Candidate candidate = candidateService.findNameByUsername(username);

        List<Skill> suggestedSkills = skillService.suggestSkillsForCandidate(candidateId);
        model.addAttribute("candidate", candidate);
        model.addAttribute("suggestedSkills", suggestedSkills);
        return "candidate-screen/suggestedSkillForCan";
    }

    @GetMapping("/suggestJob/{id}")
    public String getJobDetails(@PathVariable Long id, Model model, Principal principal) {
        Job job = jobService.findJobById(id);
        String username = principal.getName();
        Candidate candidate = candidateService.findNameByUsername(username);
        if (job == null) {
            return "redirect:/home";
        }
        model.addAttribute("job", job);
        model.addAttribute("candidate", candidate);
        return "job-screen/detailJob";
    }

    @PostMapping("/applyJob/{jobId}")
    public String applyJob(@PathVariable Long jobId, Principal principal, Model model) {
        // Lấy tên người dùng từ principal để tìm ứng viên
        String username = principal.getName();
        Candidate candidate = candidateService.findNameByUsername(username);

        // Lấy công việc theo jobId
        Job job = jobService.findJobById(jobId);
        if (job == null) {
            model.addAttribute("error", "Công việc không tồn tại.");
            return "redirect:/candidate/home";
        }
        boolean checkExisted = jobApplicationService.findCanOfJobApp(candidate, job);
        if (checkExisted) {
            throw new IllegalStateException("Bạn đã ứng tuyển vào công việc này trước đó.");
        }

        // Lưu thông tin ứng tuyển vào cơ sở dữ liệu
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(job);
        jobApplication.setCan(candidate);
        jobApplication.setApplicationDate(new Date());
        jobApplication.setStatus("Applied");

        jobApplicationService.save(jobApplication); // Lưu ứng tuyển vào database

        // Hiển thị thông báo hoặc chuyển hướng sau khi thành công
        model.addAttribute("message", "Ứng tuyển thành công vào công việc " + job.getJobName());
        return "redirect:/candidate/home"; // Hoặc chuyển hướng đến một trang khác
    }
}
