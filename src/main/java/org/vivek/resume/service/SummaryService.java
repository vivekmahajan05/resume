package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public void save(Summary summary){
        summaryRepository.save(summary);
    }

    public void saveAll(List<Summary> summaryList){
        summaryRepository.saveAll(summaryList);
    }
}
