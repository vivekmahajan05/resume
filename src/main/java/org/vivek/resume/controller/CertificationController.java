package org.vivek.resume.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.resume.entity.Certification;
import org.vivek.resume.service.CertificationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.application.resource-url}/certification/")
public class CertificationController {

    @Autowired
    private final CertificationService certificationService;

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "addCertification/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Certification> addCertification(@PathVariable(name = "id") Integer id, @RequestBody Certification certification) {
        log.debug("Add Certification: " + certification);
        return new ResponseEntity<>(certificationService.save(id, certification), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "addCertifications/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCertifications(@PathVariable(name = "id") Integer id, @RequestBody List<Certification> certifications) {
        log.debug("Add Certifications: " + certifications);
        certificationService.saveAll(id, certifications);
        return new ResponseEntity<>("Certifications Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getCertification/{id}")
    public ResponseEntity<Certification> getCertificationById(@PathVariable(name = "id") Integer id) {
        log.debug("Get Certification: " + id);
        Certification certification = certificationService.getById(id);
        return new ResponseEntity<>(certification, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getCertificationsByCandidateId/{id}")
    public ResponseEntity<List<Certification>> listCertificationsByCandidateId(@PathVariable(name = "id") Integer id) throws Exception {
        log.debug("List Certifications By Candidate Id:" + id);
        return new ResponseEntity<>(certificationService.getByCandidateId(id), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "updateCertification/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Certification> UpdateCertification(@PathVariable(name = "id") Integer id, @RequestBody Certification certification) {
        log.debug("Update Certification: " + certification);
        return new ResponseEntity<>(certificationService.updateById(id, certification), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteCertification/{id}")
    public ResponseEntity<String> deleteCertificationById(@PathVariable(name = "id") Integer id) {
        log.debug("Delete Certification: " + id);
        certificationService.deleteById(id);
        return new ResponseEntity<>("Certification deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "deleteCertificationsByCandidateId/{id}")
    public ResponseEntity<String> deleteCertificationsByCandidateId(@PathVariable(name = "id") Integer id) {
        log.debug("Delete Certifications: " + id);
        certificationService.deleteByCandidateId(id);
        return new ResponseEntity<>("Certifications deleted successfully!", HttpStatus.OK);
    }
}
