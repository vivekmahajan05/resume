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
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "addSummary/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Summary> addSummary(@PathVariable(name = "id") Integer id,@RequestBody Summary summary) {
        log.debug("Add Summary: " + summary);
        return new ResponseEntity<>(summaryService.save(id, summary), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "addSummaries/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSummaries(@PathVariable(name = "id") Integer id,@RequestBody List<Summary> summaries) {
        log.debug("Add Summaries: " + summaries);
        summaryService.saveAll(id, summaries);
        return new ResponseEntity<>("Summaries Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getSummary/{id}")
    public ResponseEntity<Summary> getSummaryById(@PathVariable(name = "id") Integer id) {
        log.debug("Get Summary: " + id);
        Summary summary = summaryService.getSummaryById(id);
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getSummariesByCandidateId/{id}")
    public ResponseEntity<List<Summary>> listSummariesByCandidateId(@PathVariable(name = "id") Integer id) throws Exception {
        log.debug("List Summaries By Candidate Id:" + id);
        return new ResponseEntity<>(summaryService.getSummaryByCandidateId(id), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "updateSummary/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Summary> UpdateSummary(@PathVariable(name = "id") Integer id, @RequestBody Summary summary) {
        log.debug("Update Summary: " + summary);
        return new ResponseEntity<>(summaryService.updateSummaryById(id, summary), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteSummary/{id}")
    public ResponseEntity<String> deleteSummaryById(@PathVariable(name = "id") Integer id) {
        log.debug("Delete summary: " + id);
        summaryService.deleteSummaryById(id);
        return new ResponseEntity<>("Summary deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteSummariesByCandidateId/{id}")
    public ResponseEntity<String> deleteSummariesByCandidateId(@PathVariable(name = "id") Integer id) {
        log.debug("Delete summaries: " + id);
        summaryService.deleteSummaryByCandidateId(id);
        return new ResponseEntity<>("Summaries deleted successfully!", HttpStatus.OK);
    }

}
