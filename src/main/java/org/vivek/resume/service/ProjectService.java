package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Project;
import org.vivek.resume.entity.ProjectResponsibility;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.resume.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project save(Integer candidateId, Project project){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        List<ProjectResponsibility> responsibilities = project.getProjectResponsibilities();
        responsibilities.forEach(responsibility -> responsibility.setCandidate(candidate));

        project.addProjectResponsibilities(responsibilities);
        project.setCandidate(candidate);
        return projectRepository.save(project);
    }

    public void saveAll(Integer candidateId, List<Project> projects){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        for(Project project : projects){
            List<ProjectResponsibility> responsibilities = project.getProjectResponsibilities();
            responsibilities.forEach(responsibility -> responsibility.setCandidate(candidate));
            project.addProjectResponsibilities(responsibilities);
        }

        projects.forEach(project -> project.setCandidate(candidate));

        projectRepository.saveAll(projects);
    }

    public Project updateById(Integer projectId, Project project){
        Project myProject = getById(projectId);

        myProject.setProjectName(project.getProjectName());
        myProject.setCompany(project.getCompany());
        myProject.setLocation(project.getLocation());
        myProject.setRole(project.getRole());
        myProject.setStartDate(project.getStartDate());
        myProject.setEndDate(project.getEndDate());
        myProject.setPresent(project.isPresent());
        myProject.setProjectSkill(project.getProjectSkill());

        myProject.setProjectResponsibilities(project.getProjectResponsibilities());

        return projectRepository.save(myProject);
    }

    public Project getById(Integer projectId){
        Optional<Project> project = Optional.of(projectRepository.findById(projectId).orElseThrow(NotFoundException::new));
        return project.get();
    }

    public List<Project> getByCandidateId(Integer candidateId){
        Optional<List<Project>> projectList = Optional.of(projectRepository.findByCandidateId(candidateId).orElseThrow(NotFoundException::new));
        return projectList.get();
    }

    public void deleteById(Integer projectId){
        projectRepository.deleteById(projectId);
    }

    public void deleteByCandidateId(Integer candidateId){
        projectRepository.deleteByCandidateId(candidateId);
    }
}
