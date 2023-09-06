package org.vivek.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.Summary;

import java.util.List;
import java.util.Optional;

public interface SummaryRepository extends JpaRepository<Summary, Integer> {
    Optional<List<Summary>> findByCandidateId(Integer candidateId);

    void deleteByCandidateId(Integer candidateId);
}
