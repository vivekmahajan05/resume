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

        Set<Summary> summaries = candidate.getSummaries();
        candidate.addSummaries(summaries);

        Set<Education> educations = candidate.getEducations();
        candidate.addEducations(educations);

        Set<Certification> certifications = candidate.getCertifications();
        candidate.addCertifications(certifications);

        Set<Skill> skills = candidate.getSkills();
        candidate.addSkills(skills);

        Set<Project> projects = candidate.getProjects();

        for (Project project: projects) {
            Set<ProjectResponsibility> projectResponsibilities = project.getProjectResponsibilities();
            project.addProjectResponsibilities(projectResponsibilities);
        }
        candidate.addProjects(projects);

        //projects.forEach(project -> project.setProjectResponsibilities());

        //Set<ProjectResponsibility> ProjectResponsibilities = candidate.getProjects();
        //candidate.getProjects().get(ProjectResponsibilities);

        Candidate savedCandidate = candidateRepository.saveAndFlush(candidate);

        return savedCandidate;
    }

    public Candidate getCandidateById(Integer id) {
        Optional<Candidate> candidate = Optional.of(candidateRepository.findById(id).orElseThrow(NotFoundException::new));

        //Collections.sort(candidate.get().getCertifications(), Comparator.comparing(Certification::getAquiredOn,Collections.reverseOrder()));

        return candidate.get();
    }

    public Candidate updateCandidate(Integer id, Candidate candidate) {
        Candidate myCandidate = getCandidateById(id);

        myCandidate.setFirstName(candidate.getFirstName());
        myCandidate.setLastName(candidate.getLastName());
        myCandidate.setMiddleName(candidate.getMiddleName());
        myCandidate.setEmail(candidate.getEmail());
        myCandidate.setPhone(candidate.getPhone());
        myCandidate.setTitle(candidate.getTitle());
        myCandidate.setLinkedinUrl(candidate.getLinkedinUrl());
        myCandidate.setProfessionalSummary(candidate.getProfessionalSummary());

        return candidateRepository.save(myCandidate);
    }

    public void deleteCandidateById(Integer id){
        candidateRepository.deleteById(id);
    }

    public List<Candidate> getAllCandidates() {
       List<Candidate> candidateList= (List<Candidate>) candidateRepository.findAll();
       return candidateList;
    }
}
