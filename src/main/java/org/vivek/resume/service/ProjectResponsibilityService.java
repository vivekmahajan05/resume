package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public ProjectResponsibility save(ProjectResponsibility projectResponsibility){
        return projectResponsibilityRepository.save(projectResponsibility);
    }

    public void saveAll(List<ProjectResponsibility> projectResponsibilities){
        projectResponsibilityRepository.saveAll(projectResponsibilities);
    }

    public ProjectResponsibility getById(Integer id){
        Optional<ProjectResponsibility> projectResponsibility = Optional.of(projectResponsibilityRepository.findById(id).orElseThrow(NotFoundException::new));
        return projectResponsibility.get();
    }

    public List<ProjectResponsibility> getByCandidateId(Integer id){
        Optional<List<ProjectResponsibility>> projectResponsibilityList = Optional.of(projectResponsibilityRepository.findByCandidateId(id).orElseThrow(NotFoundException::new));
        return projectResponsibilityList.get();
    }

    public List<ProjectResponsibility> getByProjectId(Integer id){
        Optional<List<ProjectResponsibility>> projectResponsibilityList = Optional.of(projectResponsibilityRepository.findByProjectId(id).orElseThrow(NotFoundException::new));
        return projectResponsibilityList.get();
    }

    public void deleteById(Integer id){
        projectResponsibilityRepository.deleteById(id);
    }

    public void deleteByProjectId(Integer id){
        projectResponsibilityRepository.deleteByProjectId(id);
    }

    public void deleteByCandidateID(Integer id){
        projectResponsibilityRepository.deleteByCandidateId(id);
    }
}
