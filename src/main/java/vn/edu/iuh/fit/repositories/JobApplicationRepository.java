package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.JobApplication;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> , JpaSpecificationExecutor<JobApplication> {
  boolean existsByCanAndJob(Candidate candidate, Job job);

    List<JobApplication> findByJob_Id(Long jobId);
}