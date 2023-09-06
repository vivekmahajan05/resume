package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.*;
import org.vivek.resume.repository.CandidateRepository;
import org.vivek.resume.exception.NotFoundException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public Candidate saveCandidate(Candidate candidate){

        List<Summary> summaries = candidate.getSummaries();
        candidate.addSummaries(summaries);

        List<Education> educations = candidate.getEducations();
        candidate.addEducations(educations);

        List<Certification> certifications = candidate.getCertifications();
        candidate.addCertifications(certifications);

        List<Skill> skills = candidate.getSkills();
        candidate.addSkills(skills);

        List<Project> projects = candidate.getProjects();

        for (Project project: projects) {
            List<ProjectResponsibility> projectResponsibilities = project.getProjectResponsibilities();
            project.addProjectResponsibilities(projectResponsibilities);
        }
        candidate.addProjects(projects);

        return candidateRepository.saveAndFlush(candidate);
    }

    public Candidate getCandidateById(Integer candidateId) {
        Optional<Candidate> candidate = Optional.of(candidateRepository.findById(candidateId).orElseThrow(NotFoundException::new));

        //Collections.sort(candidate.get().getCertifications(), Comparator.comparing(Certification::getAquiredOn,Collections.reverseOrder()));
        //candidate.get().getCertifications().stream().sorted(Comparator.comparing(Certification::getAquiredOn,Collections.reverseOrder())).collect(Collectors.toSet());

        return candidate.get();
    }

    public Candidate updateCandidate(Integer candidateId, Candidate candidate) {
        Candidate myCandidate = getCandidateById(candidateId);

        myCandidate.setFirstName(candidate.getFirstName());
        myCandidate.setLastName(candidate.getLastName());
        myCandidate.setMiddleName(candidate.getMiddleName());
        myCandidate.setEmail(candidate.getEmail());
        myCandidate.setPhone(candidate.getPhone());
        myCandidate.setTitle(candidate.getTitle());
        myCandidate.setLinkedinUrl(candidate.getLinkedinUrl());
        myCandidate.setProfessionalSummary(candidate.getProfessionalSummary());

        myCandidate.setSummaries(candidate.getSummaries());
        myCandidate.setEducations(candidate.getEducations());
        myCandidate.setCertifications(candidate.getCertifications());
        myCandidate.setSkills(candidate.getSkills());
        myCandidate.setProjects(candidate.getProjects());

        return candidateRepository.save(myCandidate);
    }

    public void deleteCandidateById(Integer candidateId){
        candidateRepository.deleteById(candidateId);
    }

    public List<Candidate> getAllCandidates() {
       List<Candidate> candidateList = candidateRepository.findAll();
       return candidateList;
    }
}
