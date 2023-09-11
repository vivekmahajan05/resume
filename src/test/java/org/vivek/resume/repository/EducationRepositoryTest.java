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
import org.vivek.resume.entity.Education;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EducationRepositoryTest {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveEducationTest(){

        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("EducationRepositoryTest@gmail.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        Candidate savedCandidate = candidateRepository.save(candidate);

        Education education = new Education();
        education.setCollege("Test College");
        education.setDegree("Test Degree");
        education.setSpecialization("Test Specialization");
        education.setUniversity("Test University");
        education.setGraduationYear(new Date());

        education.setCandidate(savedCandidate);

        Education education1 = educationRepository.save(education);
        Assertions.assertThat(education1.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void saveEducationListTest(){

        Candidate candidate = candidateRepository.findByEmail("EducationRepositoryTest@gmail.com").get();

        List<Education> educations = new ArrayList<>();
        for(int i=0; i <=5; i++) {

            Education education = new Education();
            education.setCollege("Test College " + i);
            education.setDegree("Test Degree " + i);
            education.setSpecialization("Test Specialization " + i);
            education.setUniversity("Test University " + i);
            education.setGraduationYear(new Date());

            education.setCandidate(candidate);
            educations.add(education);
        }

        educationRepository.saveAll(educations);

        List<Education> educationList = educationRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(educationList.size()).isGreaterThan(5);
    }

    @Test
    @Order(3)
    void findEducationByCandidateIdTest() {
        Candidate candidate = candidateRepository.findByEmail("EducationRepositoryTest@gmail.com").get();
        Optional<List<Education>> educationList = educationRepository.findByCandidateId(candidate.getId());

        List<Education> educations = null;

        if(educationList.isPresent())
            educations = educationList.get();

        Assertions.assertThat(educations).isNotNull();
    }

    @Test
    @Order(4)
    void findEducationBySummaryIdTest(){
        Optional<Education> optionalCertification = educationRepository.findById(1);

        Education education = null;
        if(optionalCertification.isPresent())
            education = optionalCertification.get();

        Assertions.assertThat(education).isNotNull();
    }

    @Test
    @Order(5)
    void updateEducationTest(){
        Optional<Education> optionalCertification = educationRepository.findById(1);

        Education education = null;
        if(optionalCertification.isPresent())
            education = optionalCertification.get();

        education.setDegree("Updated Test Degree");
        Education savedEducation = educationRepository.save(education);

        Assertions.assertThat(savedEducation.getDegree()).isEqualTo("Updated Test Degree");


    }

    @Test
    @Order(6)
    void deleteByCertificationId() {
        educationRepository.deleteById(1);

        Education education = null;
        Optional<Education> optionalEducation = educationRepository.findById(1);

        if(optionalEducation.isPresent()){
            education = optionalEducation.get();
        }

        Assertions.assertThat(education).isNull();
    }

    @Test
    @Order(7)
    void deleteByCandidateId() {
        Candidate candidate = candidateRepository.findByEmail("EducationRepositoryTest@gmail.com").get();
        educationRepository.deleteByCandidateId(candidate.getId());

        List<Education> educationList = educationRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(educationList.size()).isEqualTo(0);
    }

    @Test
    @Order(8)
    void deleteCandidateById() {
        Candidate candidate = candidateRepository.findByEmail("EducationRepositoryTest@gmail.com").get();
        candidateRepository.deleteById(candidate.getId());

        Candidate candidate1 = null;
        Optional<Candidate> optionalCandidate = candidateRepository.findById(1);

        if(optionalCandidate.isPresent()){
            candidate1 = optionalCandidate.get();
        }

        Assertions.assertThat(candidate1).isNull();
    }

}