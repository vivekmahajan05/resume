package org.vivek.resume.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.resume.entity.ProjectResponsibility;
import org.vivek.resume.service.ProjectResponsibilityService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.application.resource-url}/projectResponsibility/")
public class ProjectResponsibilityController {
    
    @Autowired
    private final ProjectResponsibilityService responsibilityService;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addProjectResponsibility/{candidateId}/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectResponsibility> addProjectResponsibility(@PathVariable(name = "candidateId") Integer candidateId,
                                                                          @PathVariable(name = "projectId") Integer projectId,
                                                                          @RequestBody ProjectResponsibility responsibility) {
        log.debug("Add ProjectResponsibility: " + responsibility);
        return new ResponseEntity<>(responsibilityService.save(candidateId, projectId, responsibility), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addProjectResponsibilities/{candidateId}/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProjectResponsibilityResponsibilities(@PathVariable(name = "candidateId") Integer candidateId,
                                                                          @PathVariable(name = "projectId") Integer projectId,
                                                                          @RequestBody List<ProjectResponsibility> responsibilities) {
        log.debug("Add ProjectResponsibilities: " + responsibilities);
        responsibilityService.saveAll(candidateId, projectId, responsibilities);
        return new ResponseEntity<>("ProjectResponsibilities Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getProjectResponsibility/{responsibilityId}")
    public ResponseEntity<ProjectResponsibility> getProjectResponsibilityById(@PathVariable(name = "responsibilityId") Integer responsibilityId) {
        log.debug("Get ProjectResponsibility: " + responsibilityId);
        ProjectResponsibility ProjectResponsibility = responsibilityService.getById(responsibilityId);
        return new ResponseEntity<>(ProjectResponsibility, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getProjectResponsibilitiesByCandidateId/{candidateId}")
    public ResponseEntity<List<ProjectResponsibility>> listProjectResponsibilitiesByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("List ProjectResponsibility By Candidate Id:" + candidateId);
        return new ResponseEntity<>(responsibilityService.getByCandidateId(candidateId), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "getProjectResponsibilitiesByProjectId/{projectId}")
    public ResponseEntity<List<ProjectResponsibility>> listProjectResponsibilitiesByProjectId(@PathVariable(name = "projectId") Integer projectId) {
        log.debug("List ProjectResponsibility By Candidate Id:" + projectId);
        return new ResponseEntity<>(responsibilityService.getByProjectId(projectId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, 
            path = "updateProjectResponsibility/{responsibilityId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectResponsibility> UpdateProjectResponsibility(@PathVariable(name = "responsibilityId") Integer responsibilityId,
                                                                             @RequestBody ProjectResponsibility projectResponsibility) {
        log.debug("Update projectResponsibility: " + projectResponsibility);
        return new ResponseEntity<>(responsibilityService.updateById(responsibilityId, projectResponsibility), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteProjectResponsibility/{responsibilityId}")
    public ResponseEntity<String> deleteProjectResponsibilityById(@PathVariable(name = "responsibilityId") Integer responsibilityId) {
        log.debug("Delete ProjectResponsibility: " + responsibilityId);
        responsibilityService.deleteById(responsibilityId);
        return new ResponseEntity<>("ProjectResponsibility deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteProjectResponsibilityByProjectId/{projectId}")
    public ResponseEntity<String> deleteProjectResponsibilityByProjectId(@PathVariable(name = "projectId") Integer projectId) {
        log.debug("Delete ProjectResponsibility: " + projectId);
        responsibilityService.deleteByProjectId(projectId);
        return new ResponseEntity<>("ProjectResponsibility deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteProjectResponsibilitiesByCandidateId/{candidateId}")
    public ResponseEntity<String> deleteProjectResponsibilitiesByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Delete ProjectResponsibilities: " + candidateId);
        responsibilityService.deleteByCandidateId(candidateId);
        return new ResponseEntity<>("ProjectResponsibilities deleted successfully!", HttpStatus.OK);
    }
}
