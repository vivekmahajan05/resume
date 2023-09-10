package org.vivek.resume.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Project;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Mock
    Project project;



    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCandidateTest() {

        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("candidate@email.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        System.out.println(candidateRepository.save(candidate));

        Assertions.assertThat(candidate.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getCandidateTest(){
        Candidate candidate = candidateRepository.findByEmail("candidate@email.com").get();

        Assertions.assertThat(candidate.getId()).isGreaterThan(0);

    }

    @Test
    @Order(3)
    public void getListOfCandidatesTest(){

        List<Candidate> candidates = candidateRepository.findAll();

        Assertions.assertThat(candidates.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCandidateTest(){

        Candidate candidate = candidateRepository.findByEmail("candidate@email.com").get();

        candidate.setEmail("testcase@gmail.com");

        Candidate candidateUpdated =  candidateRepository.save(candidate);

        Assertions.assertThat(candidateUpdated.getEmail()).isEqualTo("testcase@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteCandidateTest(){

        Candidate candidate = candidateRepository.findByEmail("testcase@gmail.com").get();
        candidateRepository.delete(candidate);

        Candidate candidate1 = null;
        Optional<Candidate> optionalCandidate = candidateRepository.findByEmail("testcase@gmail.com");

        if(optionalCandidate.isPresent()){
            candidate1 = optionalCandidate.get();
        }
        Assertions.assertThat(candidate1).isNull();
    }
}