package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Education;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.resume.repository.EducationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationService {

    private final EducationRepository educationRepository;

    public Education save(Integer candidateId, Education education){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        education.setCandidate(candidate);

        return educationRepository.save(education);
    }

    public void saveAll(Integer candidateId, List<Education> educations){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        educations.forEach(education -> education.setCandidate(candidate));
        educationRepository.saveAll(educations);
    }

    public Education getById(Integer educationId){
        Optional<Education> education = Optional.of(educationRepository.findById(educationId).orElseThrow(NotFoundException::new));
        return education.get();
    }

    public List<Education> getByCandidateId(Integer candidateId){
        Optional<List<Education>> educationList = Optional.of(educationRepository.findByCandidateId(candidateId).orElseThrow(NotFoundException::new));
        return educationList.get();
    }

    public void deleteById(Integer educationId){
        educationRepository.deleteById(educationId);
    }

    public void deleteByCandidateId(Integer candidateId){
        educationRepository.deleteByCandidateId(candidateId);
    }

    public Education updateById(Integer educationId, Education education) {
        education.setId(educationId);
        return educationRepository.save(education);
    }
}
