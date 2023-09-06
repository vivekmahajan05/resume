package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.Project;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.resume.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project save(Project project){
        return projectRepository.save(project);
    }

    public void saveAll(List<Project> projects){
        projectRepository.saveAll(projects);
    }

    public Project getById(Integer id){
        Optional<Project> project = Optional.of(projectRepository.findById(id).orElseThrow(NotFoundException::new));
        return project.get();
    }

    public List<Project> getByCandidateId(Integer id){
        Optional<List<Project>> projectList = Optional.of(projectRepository.findByCandidateId(id).orElseThrow(NotFoundException::new));
        return projectList.get();
    }

    public void deleteById(Integer id){
        projectRepository.deleteById(id);
    }

    public void deleteByCandidateID(Integer id){
        projectRepository.deleteByCandidateId(id);
    }
}
