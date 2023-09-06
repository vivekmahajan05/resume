package org.vivek.resume.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.resume.entity.Skill;
import org.vivek.resume.service.SkillService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.application.resource-url}/skill/")
public class SkillController {
    
    @Autowired
    private final SkillService skillService;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addSkill/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Skill> addSkill(@PathVariable(name = "candidateId") Integer candidateId,
                                          @RequestBody Skill skill) {
        log.debug("Add skill: " + skill);
        return new ResponseEntity<>(skillService.save(candidateId, skill), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addSkills/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSkills(@PathVariable(name = "candidateId") Integer candidateId,
                                            @RequestBody List<Skill> skills) {
        log.debug("Add skills: " + skills);
        skillService.saveAll(candidateId, skills);
        return new ResponseEntity<>("skills Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getSkill/{skillId}")
    public ResponseEntity<Skill> getSkillById(@PathVariable(name = "skillId") Integer skillId) {
        log.debug("Get Skill: " + skillId);
        Skill Skill = skillService.getById(skillId);
        return new ResponseEntity<>(Skill, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getSkillsByCandidateId/{candidateId}")
    public ResponseEntity<List<Skill>> listSkillsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("List Skills By Candidate Id:" + candidateId);
        return new ResponseEntity<>(skillService.getByCandidateId(candidateId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "updateSkill/{skillId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Skill> UpdateSkill(@PathVariable(name = "skillId") Integer skillId,
                                             @RequestBody Skill skill) {
        log.debug("Update skill: " + skill);
        return new ResponseEntity<>(skillService.updateById(skillId, skill), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteSkill/{skillId}")
    public ResponseEntity<String> deleteSkillById(@PathVariable(name = "skillId") Integer skillId) {
        log.debug("Delete Skill: " + skillId);
        skillService.deleteById(skillId);
        return new ResponseEntity<>("Skill deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteSkillsByCandidateId/{candidateId}")
    public ResponseEntity<String> deleteSkillsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Delete Skills: " + candidateId);
        skillService.deleteByCandidateId(candidateId);
        return new ResponseEntity<>("Skills deleted successfully!", HttpStatus.OK);
    }
}
