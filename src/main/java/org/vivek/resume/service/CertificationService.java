package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Certification;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.resume.repository.CertificationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public Certification save(Integer candidateId, Certification certification){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        certification.setCandidate(candidate);
        return certificationRepository.save(certification);
    }

    public void saveAll(Integer candidateId, List<Certification> certifications){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        certifications.forEach(certification -> certification.setCandidate(candidate));

        certificationRepository.saveAll(certifications);
    }

    public Certification updateById(Integer certificationId, Certification certification){
        certification.setId(getById(certificationId).getId());
        return certificationRepository.save(certification);
    }

    public Certification getById(Integer certificationId){
        Optional<Certification> certification = Optional.of(certificationRepository.findById(certificationId).orElseThrow(NotFoundException::new));
        return certification.get();
    }

    public List<Certification> getByCandidateId(Integer candidateId){
        Optional<List<Certification>> certificationList = Optional.of(certificationRepository.findByCandidateId(candidateId).orElseThrow(NotFoundException::new));
        return certificationList.get();
    }

    public void deleteById(Integer certificationId){
         Certification certification = getById(certificationId);
         certificationRepository.deleteById(certification.getId());
    }

    public void deleteByCandidateId(Integer candidateId){
        getByCandidateId(candidateId);
        certificationRepository.deleteByCandidateId(candidateId);
    }
}
