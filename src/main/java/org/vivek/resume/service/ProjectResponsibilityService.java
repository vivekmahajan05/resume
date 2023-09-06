package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Project;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.resume.entity.ProjectResponsibility;
import org.vivek.resume.repository.ProjectResponsibilityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectResponsibilityService {

    private final ProjectResponsibilityRepository projectResponsibilityRepository;

    public ProjectResponsibility save(Integer candidateId, Integer projectId, ProjectResponsibility projectResponsibility){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        Project project = new Project();
        project.setId(projectId);

        projectResponsibility.setProject(project);
        projectResponsibility.setCandidate(candidate);
        return projectResponsibilityRepository.save(projectResponsibility);
    }

    public void saveAll(Integer candidateId, Integer projectId, List<ProjectResponsibility> projectResponsibilities){

        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        Project project = new Project();
        project.setId(projectId);

        for(ProjectResponsibility projectResponsibility : projectResponsibilities){
            projectResponsibility.setProject(project);
            projectResponsibility.setCandidate(candidate);
        }

        projectResponsibilityRepository.saveAll(projectResponsibilities);
    }

    public ProjectResponsibility updateById(Integer responsibilityId, ProjectResponsibility responsibility){
        responsibility.setId(responsibilityId);
        return projectResponsibilityRepository.save(responsibility);
    }

    public ProjectResponsibility getById(Integer responsibilityId){
        Optional<ProjectResponsibility> projectResponsibility = Optional.of(projectResponsibilityRepository.findById(responsibilityId).orElseThrow(NotFoundException::new));
        return projectResponsibility.get();
    }

    public List<ProjectResponsibility> getByCandidateId(Integer candidateId){
        Optional<List<ProjectResponsibility>> projectResponsibilityList = Optional.of(projectResponsibilityRepository.findByCandidateId(candidateId).orElseThrow(NotFoundException::new));
        return projectResponsibilityList.get();
    }

    public List<ProjectResponsibility> getByProjectId(Integer projectId){
        Optional<List<ProjectResponsibility>> projectResponsibilityList = Optional.of(projectResponsibilityRepository.findByProjectId(projectId).orElseThrow(NotFoundException::new));
        return projectResponsibilityList.get();
    }

    public void deleteById(Integer responsibilityId){
        projectResponsibilityRepository.deleteById(responsibilityId);
    }

    public void deleteByProjectId(Integer projectId){
        projectResponsibilityRepository.deleteByProjectId(projectId);
    }

    public void deleteByCandidateId(Integer candidateId){
        projectResponsibilityRepository.deleteByCandidateId(candidateId);
    }
}
