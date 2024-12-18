package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Skill;
import vn.edu.iuh.fit.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.repositories.SkillRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public List<Skill> suggestSkillsForCandidate(Long candidateId) {
        // Lấy tất cả skill đã có của ứng viên
        List<Long> acquiredSkillIds = candidateSkillRepository.findByCandidateId(candidateId)
                .stream()
                .map(candidateSkill -> candidateSkill.getSkill().getId())
                .collect(Collectors.toList());

        // Lấy toàn bộ skill và loại bỏ những skill đã có
        return skillRepository.findAll()
                .stream()
                .filter(skill -> !acquiredSkillIds.contains(skill.getId()))
                .collect(Collectors.toList());
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill findById(Long skillId) {
        return skillRepository.findSkillById(skillId);
    }

    public Skill findByName(String skillName) {
        return skillRepository.findBySkillName(skillName);
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }
}
