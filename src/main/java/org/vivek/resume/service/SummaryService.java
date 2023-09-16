package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Summary;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.resume.repository.SummaryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummaryService {

    private final SummaryRepository summaryRepository;

    public Summary getSummaryById(Integer summaryId){
        Optional<Summary> summary = Optional.of(summaryRepository.findById(summaryId).orElseThrow(NotFoundException::new));
        return summary.get();
    }

    public List<Summary> getSummaryByCandidateId(Integer candidateId){
        Optional<List<Summary>> summary = Optional.of(summaryRepository.findByCandidateId(candidateId).orElseThrow(NotFoundException::new));
        return summary.get();
    }

    public Summary save(Integer candidateId, Summary summary){

        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        summary.setCandidate(candidate);
        return summaryRepository.save(summary);
    }

    public void saveAll(Integer candidateId, List<Summary> summaryList){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        summaryList.forEach(summary -> summary.setCandidate(candidate));

        summaryRepository.saveAll(summaryList);
    }

    public Summary updateSummaryById(Integer summaryId, Summary summary){
       summary.setId(summaryId);
        return summaryRepository.save(summary);
    }

    public void deleteSummaryById(Integer summaryId){
        summaryRepository.deleteById(getSummaryById(summaryId).getId());
    }

    public void deleteSummaryByCandidateId(Integer candidateId){
        getSummaryByCandidateId(candidateId);
        summaryRepository.deleteByCandidateId(candidateId);
    }
}
