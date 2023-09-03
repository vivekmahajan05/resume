package org.vivek.resume.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
}
