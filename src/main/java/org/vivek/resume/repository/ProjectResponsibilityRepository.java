package org.vivek.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.ProjectResponsibility;

import java.util.List;
import java.util.Optional;

public interface ProjectResponsibilityRepository extends JpaRepository<ProjectResponsibility, Integer> {

    Optional<List<ProjectResponsibility>> findByCandidateId(Integer id);
    Optional<List<ProjectResponsibility>> findByProjectId(Integer id);
    void deleteByCandidateId(Integer id);
    void deleteByProjectId(Integer id);
}
