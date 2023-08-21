package org.vivek.resume.dao;

import org.springframework.data.repository.CrudRepository;
import org.vivek.resume.entity.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate,Integer> {
}
