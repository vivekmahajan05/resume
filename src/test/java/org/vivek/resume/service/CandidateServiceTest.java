package org.vivek.resume.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.repository.CandidateRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CandidateServiceTest {

    @Mock
    CandidateRepository candidateRepository;

    @InjectMocks
    CandidateService candidateService;

    @Test
    void saveCandidate() {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("candidate@email.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        candidateService.saveCandidate(candidate);

        verify(candidateRepository).saveAndFlush(candidate);
    }

    @Test
    void getCandidateById() {
    }

    @Test
    void updateCandidate() {
    }

    @Test
    void deleteCandidateById() {
    }

    @Test
    void getAllCandidates() {
    }
}