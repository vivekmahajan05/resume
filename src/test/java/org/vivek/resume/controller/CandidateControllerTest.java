package org.vivek.resume.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.service.CandidateService;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@ExtendWith(MockitoExtension.class)
//@WebMvcTest(CandidateController.class)
@SpringBootTest
@RequiredArgsConstructor
class CandidateControllerTest {

    @MockBean
    CandidateService candidateService;

    ObjectMapper mapper = new ObjectMapper();

    //@Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCandidate() throws Exception {
        /*Candidate candidate = new Candidate();
        candidate.setId(1);

        Candidate savedCandidate = new Candidate();
        savedCandidate.setId(candidate.getId());

        when(candidateService.saveCandidate(any(Candidate.class))).thenReturn(savedCandidate);

        mockMvc.perform(post("/candidate/addCandidate")
                .content(mapper.writeValueAsString(candidate))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedCandidate.getId()));*/

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