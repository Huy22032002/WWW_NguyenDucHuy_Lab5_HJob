package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.JobSkill;
import vn.edu.iuh.fit.models.JobSkillId;
@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> , JpaSpecificationExecutor<JobSkill> {
  }