package org.vivek.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    Optional<List<Skill>> findByCandidateId(Integer candidateId);

    void deleteByCandidateId(Integer candidateId);
}
