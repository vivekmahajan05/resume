package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.*;
import org.vivek.resume.repository.CandidateRepository;
import org.vivek.resume.exception.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

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

        return candidateRepository.saveAndFlush(candidate);
    }

    public Candidate getCandidateById(Integer id) {
        Optional<Candidate> candidate = Optional.of(candidateRepository.findById(id).orElseThrow(NotFoundException::new));

        //Collections.sort(candidate.get().getCertifications(), Comparator.comparing(Certification::getAquiredOn,Collections.reverseOrder()));
        //candidate.get().getCertifications().stream().sorted(Comparator.comparing(Certification::getAquiredOn,Collections.reverseOrder())).collect(Collectors.toSet());

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

        myCandidate.setSummaries(candidate.getSummaries());
        myCandidate.setEducations(candidate.getEducations());
        myCandidate.setCertifications(candidate.getCertifications());
        myCandidate.setSkills(candidate.getSkills());
        myCandidate.setProjects(candidate.getProjects());

        return candidateRepository.save(myCandidate);
    }

    public void deleteCandidateById(Integer id){
        candidateRepository.deleteById(id);
    }

    public List<Candidate> getAllCandidates() {
       List<Candidate> candidateList = candidateRepository.findAll();
       return candidateList;
    }
}
