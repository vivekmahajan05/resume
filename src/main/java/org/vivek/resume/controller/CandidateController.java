package org.vivek.resume.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.service.CandidateService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.application.resource-url}/candidate/")
public class CandidateController {

    @Autowired
    private final CandidateService candidateService;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addCandidate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate) {
        log.debug("Add Candidate: " + candidate);
        //candidateService.saveCandidate(candidate);
        return new ResponseEntity<>(candidateService.saveCandidate(candidate), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getCandidate/{candidateId}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Get Candidate: " + candidateId);
        Candidate candidate = candidateService.getCandidateById(candidateId);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getCandidates")
    public ResponseEntity<List<Candidate>> listCandidates()  {
        log.debug("List Candidate");

        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "updateCandidate/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Candidate> UpdateCandidate(@PathVariable(name = "candidateId") Integer candidateId,
                                                     @RequestBody Candidate candidate) {
        log.debug("Update Candidate: " + candidate);

        return new ResponseEntity<>(candidateService.updateCandidate(candidateId, candidate), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteCandidate/{candidateId}")
    public ResponseEntity<String> deleteCandidateById(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Delete Candidate: " + candidateId);
        candidateService.deleteCandidateById(candidateId);
        return new ResponseEntity<>("Candidate deleted successfully!", HttpStatus.OK);
    }
}
