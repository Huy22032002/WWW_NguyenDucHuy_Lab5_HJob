package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.JobSkill;
import vn.edu.iuh.fit.repositories.JobSkillRepository;

@Service
public class JobSkillService {
    @Autowired
    private JobSkillRepository jobSkillRepository;

    public void save(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }
}
