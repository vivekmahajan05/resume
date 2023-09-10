package org.vivek.resume.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.Candidate;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {

    Optional<Candidate> findByEmail(String email);
}
