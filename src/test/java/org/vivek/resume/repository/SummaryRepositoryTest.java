package org.vivek.resume.repository;

import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Summary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SummaryRepositoryTest {

    @Autowired
    private SummaryRepository summaryRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveSummaryTest(){

        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("SummaryRepositoryTest@gmail.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        Candidate savedCandidate = candidateRepository.save(candidate);

        Summary summary = new Summary();
        summary.setSummaryDesc("Test Project Summary");

        summary.setCandidate(savedCandidate);

        Summary savedSummary = summaryRepository.save(summary);
        Assertions.assertThat(savedSummary.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void saveSummaryListTest(){

        Candidate candidate = candidateRepository.findByEmail("SummaryRepositoryTest@gmail.com").get();

        List<Summary> summaries = new ArrayList<>();
        for(int i=0; i <=5; i++) {

            Summary summary = new Summary();
            summary.setSummaryDesc("Test Project Summary " + i);

            summary.setCandidate(candidate);
            summaries.add(summary);
        }

        summaryRepository.saveAll(summaries);

        List<Summary> summaryList = summaryRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(summaryList.size()).isGreaterThan(5);
    }

    @Test
    @Order(3)
    void findSummaryByCandidateIdTest() {
        Candidate candidate = candidateRepository.findByEmail("SummaryRepositoryTest@gmail.com").get();
        Optional<List<Summary>> summaryList = summaryRepository.findByCandidateId(candidate.getId());

        List<Summary> summaries = null;

        if(summaryList.isPresent())
            summaries = summaryList.get();

        Assertions.assertThat(summaries).isNotNull();
    }

    @Test
    @Order(4)
    void findSummaryBySummaryIdTest(){
        Optional<Summary> optionalSummary = summaryRepository.findById(1);

        Summary summary = null;
        if(optionalSummary.isPresent())
            summary = optionalSummary.get();

        Assertions.assertThat(summary).isNotNull();
    }

    @Test
    @Order(5)
    void updateSummaryTest(){
        Optional<Summary> optionalSummary = summaryRepository.findById(1);

        Summary summary = null;
        if(optionalSummary.isPresent())
            summary = optionalSummary.get();

        summary.setSummaryDesc("Updated Test Project Summary");
        Summary savedSummary = summaryRepository.save(summary);

        Assertions.assertThat(savedSummary.getSummaryDesc()).isEqualTo("Updated Test Project Summary");


    }

    @Test
    @Order(6)
    void deleteByProjectId() {
        summaryRepository.deleteById(1);

        Summary summary = null;
        Optional<Summary> optionalSummary = summaryRepository.findById(1);

        if(optionalSummary.isPresent()){
            summary = optionalSummary.get();
        }

        Assertions.assertThat(summary).isNull();
    }

    @Test
    @Order(7)
    void deleteByCandidateId() {
        Candidate candidate = candidateRepository.findByEmail("SummaryRepositoryTest@gmail.com").get();
        summaryRepository.deleteByCandidateId(candidate.getId());

        List<Summary> summaryList = summaryRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(summaryList.size()).isEqualTo(0);
    }

    @Test
    @Order(8)
    void deleteCandidateById() {
        Candidate candidate = candidateRepository.findByEmail("SummaryRepositoryTest@gmail.com").get();
        candidateRepository.deleteById(candidate.getId());

        Candidate candidate1 = null;
        Optional<Candidate> optionalCandidate = candidateRepository.findById(1);

        if(optionalCandidate.isPresent()){
            candidate1 = optionalCandidate.get();
        }

        Assertions.assertThat(candidate1).isNull();
    }

}