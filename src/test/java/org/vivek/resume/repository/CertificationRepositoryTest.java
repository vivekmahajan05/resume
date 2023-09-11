package org.vivek.resume.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Certification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CertificationRepositoryTest {

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveCertificationTest(){

        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("CertificationRepositoryTest@gmail.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        Candidate savedCandidate = candidateRepository.save(candidate);

        Certification certification = new Certification();
        certification.setTitle("Test Title");
        certification.setAquiredOn(new Date());
        certification.setVersion("Test version");

        certification.setCandidate(savedCandidate);

        Certification certification1 = certificationRepository.save(certification);
        Assertions.assertThat(certification1.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void saveCertificationListTest(){

        Candidate candidate = candidateRepository.findByEmail("CertificationRepositoryTest@gmail.com").get();

        List<Certification> certifications = new ArrayList<>();
        for(int i=0; i <=5; i++) {

            Certification certification = new Certification();
            certification.setTitle("Test Title " + i);
            certification.setAquiredOn(new Date());
            certification.setVersion("Test version " + i);

            certification.setCandidate(candidate);
            certifications.add(certification);
        }

        certificationRepository.saveAll(certifications);

        List<Certification> certificationList = certificationRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(certificationList.size()).isGreaterThan(5);
    }

    @Test
    @Order(3)
    void findCertificationByCandidateIdTest() {
        Candidate candidate = candidateRepository.findByEmail("CertificationRepositoryTest@gmail.com").get();
        Optional<List<Certification>> certificationList = certificationRepository.findByCandidateId(candidate.getId());

        List<Certification> certifications = null;

        if(certificationList.isPresent())
            certifications = certificationList.get();

        Assertions.assertThat(certifications).isNotNull();
    }

    @Test
    @Order(4)
    void findCertificationBySummaryIdTest(){
        Optional<Certification> optionalCertification = certificationRepository.findById(1);

        Certification certification = null;
        if(optionalCertification.isPresent())
            certification = optionalCertification.get();

        Assertions.assertThat(certification).isNotNull();
    }

    @Test
    @Order(5)
    void updateCertificationTest(){
        Optional<Certification> optionalCertification = certificationRepository.findById(1);

        Certification certification = null;
        if(optionalCertification.isPresent())
            certification = optionalCertification.get();

        certification.setTitle("Updated Test Title");
        Certification savedCertification = certificationRepository.save(certification);

        Assertions.assertThat(savedCertification.getTitle()).isEqualTo("Updated Test Title");


    }

    @Test
    @Order(6)
    void deleteByCertificationId() {
        certificationRepository.deleteById(1);

        Certification certification = null;
        Optional<Certification> optionalCertification = certificationRepository.findById(1);

        if(optionalCertification.isPresent()){
            certification = optionalCertification.get();
        }

        Assertions.assertThat(certification).isNull();
    }

    @Test
    @Order(7)
    void deleteByCandidateId() {
        Candidate candidate = candidateRepository.findByEmail("CertificationRepositoryTest@gmail.com").get();
        certificationRepository.deleteByCandidateId(candidate.getId());

        List<Certification> certificationList = certificationRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(certificationList.size()).isEqualTo(0);
    }

    @Test
    @Order(8)
    void deleteCandidateById() {
        Candidate candidate = candidateRepository.findByEmail("CertificationRepositoryTest@gmail.com").get();
        candidateRepository.deleteById(candidate.getId());

        Candidate candidate1 = null;
        Optional<Candidate> optionalCandidate = candidateRepository.findById(1);

        if(optionalCandidate.isPresent()){
            candidate1 = optionalCandidate.get();
        }

        Assertions.assertThat(candidate1).isNull();
    }

}