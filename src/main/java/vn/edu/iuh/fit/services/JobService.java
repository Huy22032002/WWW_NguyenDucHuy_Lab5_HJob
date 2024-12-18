package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.*;
import vn.edu.iuh.fit.repositories.CandidateRepository;
import vn.edu.iuh.fit.repositories.JobRepository;
import vn.edu.iuh.fit.repositories.SkillRepository;

import java.util.*;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Job> recommendJobsForCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        List<CandidateSkill> candidateSkills = candidate.getCandidateSkills();
        List<Job> recommendedJobs = new ArrayList<>();

        // Dùng một Set để loại bỏ các công việc trùng lặp
        Set<Job> filteredJobs = new HashSet<>();

        // Lặp qua các kỹ năng của ứng viên và tìm công việc yêu cầu các kỹ năng đó
        for (CandidateSkill candidateSkill : candidateSkills) {
            Skill skill = candidateSkill.getSkill();
            // Lấy các công việc yêu cầu kỹ năng này
            List<Job> jobs = jobRepository.findByRequiredSkill(skill.getId());
            // Thêm các công việc vào Set (Set sẽ tự động loại bỏ công việc trùng lặp)
            filteredJobs.addAll(jobs);
        }
        recommendedJobs = new ArrayList<>(filteredJobs);
        return recommendedJobs;
    }
    public Job findJobById(Long id){
        return jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public void createJob(Job job) {
        jobRepository.save(job);
    }

    public List<Job> findAll(){
        return jobRepository.findAll();
    }

    public List<Job> findJobsByCompany(Company company) {
        return jobRepository.findByCompanyId(company.getId());
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

}

