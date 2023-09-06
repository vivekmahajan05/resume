package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public Education save(Education education){
        return educationRepository.save(education);
    }

    public void saveAll(List<Education> educations){
        educationRepository.saveAll(educations);
    }

    public Education getById(Integer id){
        Optional<Education> education = Optional.of(educationRepository.findById(id).orElseThrow(NotFoundException::new));
        return education.get();
    }

    public List<Education> getByCandidateId(Integer id){
        Optional<List<Education>> educationList = Optional.of(educationRepository.findByCandidateId(id).orElseThrow(NotFoundException::new));
        return educationList.get();
    }

    public void deleteById(Integer id){
        educationRepository.deleteById(id);
    }

    public void deleteByCandidateID(Integer id){
        educationRepository.deleteByCandidateId(id);
    }
}