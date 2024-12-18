package vn.edu.iuh.fit.controllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.models.*;
import vn.edu.iuh.fit.repositories.SkillRepository;
import vn.edu.iuh.fit.services.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller()
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobService jobService;
    @Autowired
    private JobSkillService jobSkillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobApplicationService jobApplicationService;
    public String listCompanies(Model model) {

        model.addAttribute("lstCompany", companyService.findAll()) ;
        return "company/list";
    }
    @GetMapping("/findCandidates")
    public String showFindCandidatesPage(Model model, Principal principal) {
        String username = principal.getName();
        Company company = companyService.findNameByUsername(username);

        model.addAttribute("company", company);
        model.addAttribute("allSkills", skillService.findAll());
        return "company-screen/findProperCandidate";
    }

    @PostMapping("/findCandidates")
    public String findCandidates(@RequestParam("skillIds") List<Long> skillIds, Model model) {
        List<Candidate> candidates = candidateService.findCandidatesBySkills(skillIds);
        model.addAttribute("matchingCandidates", candidates);
        return "company-screen/findProperCandidate";
    }

    @GetMapping("job/{id}/lstCanApply")
    public String showApplicantsList(@PathVariable("id") Long jobId, Model model) {
        // Lấy thông tin công việc theo id
        Job job = jobService.findJobById(jobId);
        if (job != null) {
            // Lấy danh sách ứng viên có thể ứng tuyển vào công việc này
            List<JobApplication> applicants = jobApplicationService.getApplicantsForJob(jobId);
            model.addAttribute("job", job);
            model.addAttribute("applicants", applicants);
        }
        return "company-screen/lstAppliedCandidates";
    }

    // Hiển thị danh sách các công việc mà công ty đã đăng
    @GetMapping("/lstJob")
    public String getCompanyJobs(Model model, Principal principal) {
        String username = principal.getName();
        Company company = companyService.findNameByUsername(username);
        List<Job> lstJob = jobService.findJobsByCompany(company);

        model.addAttribute("company", company);
        model.addAttribute("lstJob", lstJob);

        return "company-screen/listJob";
    }
    // Trang tạo tin tuyển dụng
    @GetMapping("/createJob")
    public String createJobForm(Model model, Principal principal) {
        String username = principal.getName();
        Company company = companyService.findNameByUsername(username);
        List<Skill> skills = skillService.findAll();
        model.addAttribute("skills", skills);
        model.addAttribute("company", company);

        return "company-screen/createdJob";
    }
    @PostMapping("/createJob")
    public String createJob(
            @RequestParam String jobName,
            @RequestParam String jobDesc,
            @RequestParam BigDecimal salaryRangeMin,
            @RequestParam BigDecimal salaryRangeMax,
            @RequestParam String jobType,
            @RequestParam List<String> skillNames,
            @RequestParam List<Byte> skillTypes,
            @RequestParam List<String> skillDescs,
            @RequestParam List<String> skillLevels,
            @RequestParam List<String> moreInfos,
            Principal principal) {
        // Kiểm tra đầu vào
        if (skillNames == null || skillTypes == null || skillDescs == null ||
                skillLevels == null || skillNames.size() != skillTypes.size() ||
                skillNames.size() != skillDescs.size() ||
                skillNames.size() != skillLevels.size()) {
            throw new IllegalArgumentException("Danh sách kỹ năng và thông tin không khớp!");
        }

        String username = principal.getName();
        Company company = companyService.findNameByUsername(username);
        if (company == null) {
            throw new RuntimeException("Công ty không tồn tại");
        }

        // Tạo job mới
        Job job = new Job();
        job.setJobName(jobName);
        job.setJobDesc(jobDesc);
        job.setSalaryRangeMin(salaryRangeMin);
        job.setSalaryRangeMax(salaryRangeMax);
        job.setJobType(jobType);
        job.setCompany(company);

        // Lưu job vào database
        job = jobService.save(job);

        // Lưu kỹ năng vào bảng job_skill
        for (int i = 0; i < skillNames.size(); i++) {
            String skillName = skillNames.get(i);
            Byte skillType = skillTypes.get(i);
            String skillDesc = skillDescs.get(i);
            String skillLevel = skillLevels.get(i);
            String moreInfo = moreInfos.get(i);

            // Tìm hoặc tạo kỹ năng dựa trên tên kỹ năng
            Skill skill = skillService.findByName(skillName);
            if (skill == null) {
                skill = new Skill();
                skill.setSkillName(skillName);
                skill.setSkillType(skillType);
                skill.setSkillDesc(skillDesc);
                skill = skillService.save(skill);
            }

            JobSkill jobSkill = new JobSkill();
            JobSkillId jobSkillId = new JobSkillId();
            jobSkillId.setJobId(job.getId());
            jobSkillId.setSkillId(skill.getId());
            jobSkill.setId(jobSkillId);

            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkill.setSkillLevel(skillLevel);
            jobSkill.setMoreInfos(moreInfo);
            jobSkillService.save(jobSkill);
        }

        return "redirect:/company/lstJob";
    }


    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        String username = principal.getName();
        Company company = companyService.findNameByUsername(username);

        model.addAttribute("company", company);
        return "company-screen/home";
    }
}
