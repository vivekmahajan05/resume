package org.vivek.resume.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.resume.entity.Project;
import org.vivek.resume.service.ProjectService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.application.resource-url}/project/")
public class ProjectController {
    
    @Autowired
    private final ProjectService projectService;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addProject/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> addProject(@PathVariable(name = "candidateId") Integer candidateId,
                                              @RequestBody Project project) {
        log.debug("Add Project: " + project);
        return new ResponseEntity<>(projectService.save(candidateId, project), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addProjects/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProjects(@PathVariable(name = "candidateId") Integer candidateId,
                                              @RequestBody List<Project> projects) {
        log.debug("Add Projects: " + projects);
        projectService.saveAll(candidateId, projects);
        return new ResponseEntity<>("Projects Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getProject/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable(name = "projectId") Integer projectId) {
        log.debug("Get Project: " + projectId);
        Project Project = projectService.getById(projectId);
        return new ResponseEntity<>(Project, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getProjectsByCandidateId/{candidateId}")
    public ResponseEntity<List<Project>> listProjectsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("List Projects By Candidate Id:" + candidateId);
        return new ResponseEntity<>(projectService.getByCandidateId(candidateId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "updateProject/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> UpdateProject(@PathVariable(name = "projectId") Integer projectId,
                                                 @RequestBody Project project) {
        log.debug("Update project: " + project);
        return new ResponseEntity<>(projectService.updateById(projectId, project), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteProject/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable(name = "projectId") Integer projectId) {
        log.debug("Delete Project: " + projectId);
        projectService.deleteById(projectId);
        return new ResponseEntity<>("Project deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteProjectsByCandidateId/{candidateId}")
    public ResponseEntity<String> deleteProjectsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Delete Projects: " + candidateId);
        projectService.deleteByCandidateId(candidateId);
        return new ResponseEntity<>("Projects deleted successfully!", HttpStatus.OK);
    }
}
