package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Skill;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> , JpaSpecificationExecutor<Skill> {

  @Query("SELECT s FROM Skill s WHERE s.id = :skillId")
  Skill findSkillById(@Param("skillId") Long skillId);

  Skill findBySkillName(String skillName);
}