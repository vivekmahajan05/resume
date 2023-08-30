package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vivek.resume.dao.CandidateRepository;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public Candidate saveCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate getCandidateById(Integer id) {
        Optional<Candidate> candidate = Optional.of(candidateRepository.findById(id).orElseThrow(NotFoundException::new));
        return candidate.get();
    }

    public Candidate updateCandidate(Integer id, Candidate candidate) {
        Candidate myCandidate = getCandidateById(id);

        myCandidate.setFirstName(candidate.getFirstName());
        myCandidate.setLastName(candidate.getLastName());
        myCandidate.setMiddleName(candidate.getMiddleName());
        myCandidate.setEmail(candidate.getEmail());
        myCandidate.setPhone(candidate.getPhone());
        myCandidate.setTitle(candidate.getTitle());
        myCandidate.setLinkedinUrl(candidate.getLinkedinUrl());
        myCandidate.setProfessionalSummary(candidate.getProfessionalSummary());

        return candidateRepository.save(myCandidate);
    }

    public void deleteCandidateById(Integer id){
        candidateRepository.deleteById(id);
    }

    public List<Candidate> getAllCandidates() {
       List<Candidate> candidateList= (List<Candidate>) candidateRepository.findAll();
       return candidateList;
    }
}
