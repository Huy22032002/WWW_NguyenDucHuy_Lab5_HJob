package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Job;
import vn.edu.iuh.fit.models.Skill;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> , JpaSpecificationExecutor<Job> {
  @Query("SELECT j FROM Job j " +
          "JOIN JobSkill js ON j.id = js.job.id " +
          "JOIN Skill s ON js.skill.id = s.id " +
          "WHERE s.id = :skillId")
  List<Job> findByRequiredSkill(@Param("skillId") Long skillId);

    List<Job> findByCompanyId(Long id);
}