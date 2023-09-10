package org.vivek.resume.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CandidateRepository candidateRepository;
    @Test
    @Order(1)
    @Rollback(value = false)
    void saveProjectTest(){

        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("candidate@email.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        Candidate savedCandidate = candidateRepository.save(candidate);

        Project project = new Project();
        project.setProjectName("Test ProjectName");
        project.setCompany("Test company");
        project.setLocation("Test location");
        project.setRole("Test Role");

        project.setCandidate(savedCandidate);

        Project savedProject = projectRepository.save(project);
        Assertions.assertThat(project.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    void findProjectByCandidateIdTest() {
        Optional<List<Project>> projectList = projectRepository.findByCandidateId(1);

        List<Project> projects = null;

        if(projectList.isPresent())
            projects = projectList.get();

        System.out.println(projects);

        Assertions.assertThat(projects).isNotNull();
    }

    @Test
    @Order(2)
    void findProjectByProjectIdTest(){
        Optional<Project> optionalProject = projectRepository.findById(1);

        Project project = null;
        if(optionalProject.isPresent())
            project = optionalProject.get();

        Assertions.assertThat(project).isNotNull();
    }

    @Test
    @Order(3)
    void updateProjectTest(){
        Optional<Project> optionalProject = projectRepository.findById(1);

        Project project = null;
        if(optionalProject.isPresent())
            project = optionalProject.get();

        project.setProjectName("Updated Test Project Name");
        Project savedProject = projectRepository.save(project);

        Assertions.assertThat(savedProject.getProjectName()).isEqualTo("Updated Test Project Name");


    }

    @Test
    @Order(4)
    void deleteByProjectId() {
    }

    @Test
    @Order(5)
    void deleteByCandidateId() {
    }
}