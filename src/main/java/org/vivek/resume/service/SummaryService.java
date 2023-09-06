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

    public Summary getSummaryById(Integer id){
        Optional<Summary> summary = Optional.of(summaryRepository.findById(id).orElseThrow(NotFoundException::new));
        return summary.get();
    }

    public List<Summary> getSummaryByCandidateId(Integer id){
        Optional<List<Summary>> summary = Optional.of(summaryRepository.findByCandidateId(id).orElseThrow(NotFoundException::new));
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

    public Summary updateSummaryById(Integer id, Summary summary){

        Summary mySummary = new Summary();
        mySummary.setId(id);
        mySummary.setSummaryDesc(summary.getSummaryDesc());

        return summaryRepository.save(mySummary);
    }

    public void deleteSummaryById(Integer id){
        summaryRepository.deleteById(id);
    }

    public void deleteSummaryByCandidateId(Integer id){
        summaryRepository.deleteByCandidateId(id);
    }
}
