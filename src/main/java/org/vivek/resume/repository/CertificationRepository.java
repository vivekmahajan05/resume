package org.vivek.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.Certification;

import java.util.List;
import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {
    Optional<List<Certification>> findByCandidateId(Integer id);

    void deleteByCandidateId(Integer id);
}
