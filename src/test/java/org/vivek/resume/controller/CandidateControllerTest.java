package org.vivek.resume.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.service.CandidateService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {

    @Mock
    CandidateService candidateService;

    @Mock
    Candidate candidate;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCandidate() {

    }

    @Test
    void getCandidateById() {
    }

    @Test
    void listCandidates() {
    }

    @Test
    void updateCandidate() {
    }

    @Test
    void deleteCandidateById() {
    }
}