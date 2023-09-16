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

        candidate.get().getProjects().stream().filter(Project::isPresent).forEach(project -> project.setEndDate(new Date()));


        candidate.get().getCertifications().sort(Comparator.comparing(Certification::getAquiredOn,Collections.reverseOrder()));
        candidate.get().getEducations().sort(Comparator.comparing(Education::getGraduationYear,Collections.reverseOrder()));
        candidate.get().getProjects().sort(Comparator.comparing(Project::getEndDate,Collections.reverseOrder()));

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
        Candidate myCandidate = getCandidateById(candidateId);
        candidateRepository.deleteById(myCandidate.getId());
    }

    public List<Candidate> getAllCandidates() {
       return candidateRepository.findAll();
    }
}
