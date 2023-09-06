package org.vivek.resume.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.resume.entity.Education;
import org.vivek.resume.service.EducationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.application.resource-url}/education/")
public class EducationController {

    @Autowired
    private final EducationService educationService;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addEducation/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Education> addEducation(@PathVariable(name = "candidateId") Integer candidateId,
                                                  @RequestBody Education education) {
        log.debug("Add Education: " + education);
        return new ResponseEntity<>(educationService.save(candidateId, education), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addEducations/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEducations(@PathVariable(name = "candidateId") Integer candidateId,
                                                @RequestBody List<Education> educations) {
        log.debug("Add Educations: " + educations);
        educationService.saveAll(candidateId, educations);
        return new ResponseEntity<>("Educations Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getEducation/{educationId}")
    public ResponseEntity<Education> getEducationById(@PathVariable(name = "educationId") Integer educationId) {
        log.debug("Get Education: " + educationId);
        Education Education = educationService.getById(educationId);
        return new ResponseEntity<>(Education, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getEducationsByCandidateId/{candidateId}")
    public ResponseEntity<List<Education>> listEducationsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("List Educations By Candidate Id:" + candidateId);
        return new ResponseEntity<>(educationService.getByCandidateId(candidateId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "updateEducation/{educationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Education> UpdateEducation(@PathVariable(name = "educationId") Integer educationId,
                                                     @RequestBody Education education) {
        log.debug("Update Education: " + education);
        return new ResponseEntity<>(educationService.updateById(educationId, education), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteEducation/{educationId}")
    public ResponseEntity<String> deleteEducationById(@PathVariable(name = "id") Integer educationId) {
        log.debug("Delete Education: " + educationId);
        educationService.deleteById(educationId);
        return new ResponseEntity<>("Education deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteEducationsByCandidateId/{candidateId}")
    public ResponseEntity<String> deleteEducationsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Delete Educations: " + candidateId);
        educationService.deleteByCandidateId(candidateId);
        return new ResponseEntity<>("Educations deleted successfully!", HttpStatus.OK);
    }
}
