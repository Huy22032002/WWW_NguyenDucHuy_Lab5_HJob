package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Candidate;
import vn.edu.iuh.fit.models.Experience;
import vn.edu.iuh.fit.repositories.ExperienceRepository;

import java.util.List;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> getByCandidate(Long id) {
        return experienceRepository.findByCanId(id);
    }
}
