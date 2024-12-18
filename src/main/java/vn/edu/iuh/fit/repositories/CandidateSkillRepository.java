package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.CandidateSkill;
import vn.edu.iuh.fit.models.CandidateSkillId;

import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> , JpaSpecificationExecutor<CandidateSkill> {
    List<CandidateSkill> findByCandidateId(Long candidateId);
}