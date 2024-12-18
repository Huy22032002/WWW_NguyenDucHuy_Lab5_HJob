package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.CandidateSkill;
import vn.edu.iuh.fit.models.UserAccount;
import vn.edu.iuh.fit.repositories.CandidateRepository;
import vn.edu.iuh.fit.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.repositories.UserAccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElseThrow();
    }
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
    public void delete(Candidate candidate) {
        candidateRepository.delete(candidate);
    }

    public Candidate findNameByUsername(String username) {
        UserAccount acc = userAccountRepository.findByUsername(username);
        Long id = acc.getId();
        return candidateRepository.findById(id).orElseThrow();
    }
    public List<CandidateSkill> getAllSkillsOfCan(Long id) {
        return candidateSkillRepository.findByCandidateId(id);
    }

    public List<Candidate> findCandidatesBySkills(List<Long> skillIds) {
        if (skillIds == null || skillIds.isEmpty()) {
            return new ArrayList<>();
        }
        Long skillCount = (long) skillIds.size();
        return candidateRepository.findCandidatesBySkills(skillIds, skillCount);
    }
}
