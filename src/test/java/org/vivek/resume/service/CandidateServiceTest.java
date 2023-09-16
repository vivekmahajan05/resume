package org.vivek.resume.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.repository.CandidateRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CandidateServiceTest {

    @Mock
    CandidateRepository candidateRepository;

    @InjectMocks
    CandidateService candidateService;

    @Test
    @Order(1)
    void saveCandidate() {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("CandidateServiceTest@email.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        candidateService.saveCandidate(candidate);

        verify(candidateRepository).saveAndFlush(candidate);
    }

    @Test
    @Order(2)
    void getCandidateById() {
        Optional<Candidate> optionalCandidate = Optional.of(new Candidate());
        when(candidateRepository.findById(any(Integer.class))).thenReturn( optionalCandidate);

        candidateService.getCandidateById(any(Integer.class));

        verify(candidateRepository).findById(any(Integer.class));
    }

    @Test
    void updateCandidate() {
        Optional<Candidate> optionalCandidate = Optional.of(new Candidate());
        when(candidateRepository.findById(any(Integer.class))).thenReturn( optionalCandidate);

        Candidate candidate = new Candidate();
        candidateService.updateCandidate(any(Integer.class),candidate);

        verify(candidateRepository).save(candidate);
    }

    @Test
    void deleteCandidateById() {
        Optional<Candidate> optionalCandidate = Optional.of(new Candidate());
        when(candidateRepository.findById(any(Integer.class))).thenReturn( optionalCandidate);

        candidateService.deleteCandidateById(any(Integer.class));

        verify(candidateRepository).findById(any(Integer.class));
        verify(candidateRepository).deleteById(any(Integer.class));
    }

    @Test
    void getAllCandidates() {
        candidateService.getAllCandidates();
        verify(candidateRepository).findAll();
    }
}