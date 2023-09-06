package org.vivek.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.Education;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education,Integer> {
    Optional<List<Education>> findByCandidateId(Integer candidateId);

    void deleteByCandidateId(Integer candidateId);
}
