package org.vivek.resume.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.resume.entity.Summary;
import org.vivek.resume.service.SummaryService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.application.resource-url}/summary/")
public class SummaryController {

    @Autowired
    private final SummaryService summaryService;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addSummary/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Summary> addSummary(@PathVariable(name = "candidateId") Integer candidateId,
                                              @RequestBody Summary summary) {
        log.debug("Add Summary: " + summary);
        return new ResponseEntity<>(summaryService.save(candidateId, summary), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addSummaries/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSummaries(@PathVariable(name = "candidateId") Integer candidateId,
                                               @RequestBody List<Summary> summaries) {
        log.debug("Add Summaries: " + summaries);
        summaryService.saveAll(candidateId, summaries);
        return new ResponseEntity<>("Summaries Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getSummary/{summaryId}")
    public ResponseEntity<Summary> getSummaryById(@PathVariable(name = "summaryId") Integer summaryId) {
        log.debug("Get Summary: " + summaryId);
        Summary summary = summaryService.getSummaryById(summaryId);
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getSummariesByCandidateId/{candidateId}")
    public ResponseEntity<List<Summary>> listSummariesByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("List Summaries By Candidate Id:" + candidateId);
        return new ResponseEntity<>(summaryService.getSummaryByCandidateId(candidateId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "updateSummary/{summaryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Summary> UpdateSummary(@PathVariable(name = "summaryId") Integer summaryId,
                                                 @RequestBody Summary summary) {
        log.debug("Update Summary: " + summary);
        return new ResponseEntity<>(summaryService.updateSummaryById(summaryId, summary), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteSummary/{summaryId}")
    public ResponseEntity<String> deleteSummaryById(@PathVariable(name = "summaryId") Integer summaryId) {
        log.debug("Delete summary: " + summaryId);
        summaryService.deleteSummaryById(summaryId);
        return new ResponseEntity<>("Summary deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteSummariesByCandidateId/{candidateId}")
    public ResponseEntity<String> deleteSummariesByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Delete summaries: " + candidateId);
        summaryService.deleteSummaryByCandidateId(candidateId);
        return new ResponseEntity<>("Summaries deleted successfully!", HttpStatus.OK);
    }

}
