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
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addCertification/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Certification> addCertification(@PathVariable(name = "candidateId") Integer candidateId,
                                                          @RequestBody Certification certification) {
        log.debug("Add Certification: " + certification);
        return new ResponseEntity<>(certificationService.save(candidateId, certification), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "addCertifications/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCertifications(@PathVariable(name = "candidateId") Integer candidateId,
                                                    @RequestBody List<Certification> certifications) {
        log.debug("Add Certifications: " + certifications);
        certificationService.saveAll(candidateId, certifications);
        return new ResponseEntity<>("Certifications Added", HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "getCertification/{certificationId}")
    public ResponseEntity<Certification> getCertificationById(@PathVariable(name = "certificationId") Integer certificationId) {
        log.debug("Get Certification: " + certificationId);
        Certification certification = certificationService.getById(certificationId);
        return new ResponseEntity<>(certification, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "getCertificationsByCandidateId/{candidateId}")
    public ResponseEntity<List<Certification>> listCertificationsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("List Certifications By Candidate Id:" + candidateId);
        return new ResponseEntity<>(certificationService.getByCandidateId(candidateId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "updateCertification/{certificationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Certification> UpdateCertification(@PathVariable(name = "certificationId") Integer certificationId, @RequestBody Certification certification) {
        log.debug("Update Certification: " + certification);
        return new ResponseEntity<>(certificationService.updateById(certificationId, certification), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "deleteCertification/{certificationId}")
    public ResponseEntity<String> deleteCertificationById(@PathVariable(name = "certificationId") Integer certificationId) {
        log.debug("Delete Certification: " + certificationId);
        certificationService.deleteById(certificationId);
        return new ResponseEntity<>("Certification deleted successfully!", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            path = "deleteCertificationsByCandidateId/{candidateId}")
    public ResponseEntity<String> deleteCertificationsByCandidateId(@PathVariable(name = "candidateId") Integer candidateId) {
        log.debug("Delete Certifications: " + candidateId);
        certificationService.deleteByCandidateId(candidateId);
        return new ResponseEntity<>("Certifications deleted successfully!", HttpStatus.OK);
    }
}
