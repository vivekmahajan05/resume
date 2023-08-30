package org.vivek.resume.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
@RequestMapping("/resumeApi/v1/candidate/")
public class CandidateController {

    @Autowired
    private final CandidateService candidateService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "addCandidate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate) {
        log.debug(" Adding Candidate", candidate);

        return new ResponseEntity<>(candidateService.saveCandidate(candidate), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getCandidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable(name = "id", required = true) Integer id) {
        log.info(" Get Candidate", id);
        Candidate candidate = candidateService.getCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getCandidates")
    public ResponseEntity<List<Candidate>> listCandidates() throws Exception {
        log.info(" List Candidate");

        return new ResponseEntity<>(candidateService.getAllCandidates(), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "updateCandidate/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Candidate> UpdateCandidate(@PathVariable(name = "id", required = true) Integer id, @RequestBody Candidate candidate) {
        log.info(" Update Candidate", candidate);

        return new ResponseEntity<>(candidateService.updateCandidate(id, candidate), HttpStatus.OK);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteCandidate/{id}")
    public ResponseEntity<String> deleteCandidateById(@PathVariable(name = "id", required = true) Integer id) {
        log.info(" Delete Candidate", id);
        candidateService.deleteCandidateById(id);
        return new ResponseEntity<>("Candidate deleted successfully!", HttpStatus.OK);
    }
}
