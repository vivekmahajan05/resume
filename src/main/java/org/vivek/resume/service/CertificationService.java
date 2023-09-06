package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public Certification save(Certification certification){
        return certificationRepository.save(certification);
    }

    public void saveAll(List<Certification> certifications){
        certificationRepository.saveAll(certifications);
    }

    public Certification updateById(Integer id, Certification certification){
        certification.setId(id);
        return certificationRepository.save(certification);
    }

    public Certification getById(Integer id){
        Optional<Certification> certification = Optional.of(certificationRepository.findById(id).orElseThrow(NotFoundException::new));
        return certification.get();
    }

    public List<Certification> getByCandidateId(Integer id){
        Optional<List<Certification>> certificationList = Optional.of(certificationRepository.findByCandidateId(id).orElseThrow(NotFoundException::new));
        return certificationList.get();
    }

    public void deleteById(Integer id){
        certificationRepository.deleteById(id);
    }

    public void deleteByCandidateId(Integer id){
        certificationRepository.deleteByCandidateId(id);
    }
}
