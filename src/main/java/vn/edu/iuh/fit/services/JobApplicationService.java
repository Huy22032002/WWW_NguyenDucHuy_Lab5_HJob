package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.JobApplication;
import vn.edu.iuh.fit.repositories.JobApplicationRepository;

import java.util.List;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public void save(JobApplication jobApplication) {
        jobApplicationRepository.save(jobApplication);
    }

    public boolean findCanOfJobApp(Candidate candidate, Job job) {
        return jobApplicationRepository.existsByCanAndJob(candidate, job);
    }

    public List<JobApplication> getApplicantsForJob(Long jobId) {
        return jobApplicationRepository.findByJob_Id(jobId);
    }
}
