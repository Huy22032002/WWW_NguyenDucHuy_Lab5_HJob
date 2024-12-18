package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Candidate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> , JpaSpecificationExecutor<Candidate> {
    @Query("SELECT DISTINCT c FROM Candidate c " +
            "JOIN c.candidateSkills cs " +
            "JOIN cs.skill s " +
            "WHERE s.id IN :skillIds " +
            "GROUP BY c.id " +
            "HAVING COUNT(DISTINCT s.id) = :skillCount")
    List<Candidate> findCandidatesBySkills(@Param("skillIds") List<Long> skillIds,
                                           @Param("skillCount") Long skillCount);
}