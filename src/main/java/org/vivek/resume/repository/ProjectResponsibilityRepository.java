package org.vivek.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.ProjectResponsibility;

import java.util.List;
import java.util.Optional;

public interface ProjectResponsibilityRepository extends JpaRepository<ProjectResponsibility, Integer> {

    Optional<List<ProjectResponsibility>> findByCandidateId(Integer candidateId);
    Optional<List<ProjectResponsibility>> findByProjectId(Integer projectId);
    void deleteByCandidateId(Integer candidateId);
    void deleteByProjectId(Integer projectId);
}
