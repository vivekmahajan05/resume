package org.vivek.resume.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
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
        Assertions.assertThat(savedProject.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void saveProjectListTest(){

        Candidate candidate = candidateRepository.findByEmail("candidate@email.com").get();

        List<Project> projects = new ArrayList<>();
        for(int i=1; i <=5; i++) {

            Project project = new Project();
            project.setProjectName("Test ProjectName");
            project.setCompany("Test company");
            project.setLocation("Test location");
            project.setRole("Test Role");

            project.setCandidate(candidate);
            projects.add(project);
        }

        projectRepository.saveAll(projects);

        List<Project> projectList = projectRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(projectList.size()).isEqualTo(6);
    }

    @Test
    @Order(3)
    void findProjectByCandidateIdTest() {
        Candidate candidate = candidateRepository.findByEmail("candidate@email.com").get();
        Optional<List<Project>> projectList = projectRepository.findByCandidateId(candidate.getId());

        List<Project> projects = null;

        if(projectList.isPresent())
            projects = projectList.get();

        System.out.println(projects);

        Assertions.assertThat(projects).isNotNull();
    }

    @Test
    @Order(4)
    void findProjectByProjectIdTest(){
        Optional<Project> optionalProject = projectRepository.findById(1);

        Project project = null;
        if(optionalProject.isPresent())
            project = optionalProject.get();

        Assertions.assertThat(project).isNotNull();
    }

    @Test
    @Order(5)
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
    @Order(6)
    void deleteByProjectId() {
        projectRepository.deleteById(1);

        Project project = null;
        Optional<Project> optionalProject = projectRepository.findById(1);

        if(optionalProject.isPresent()){
            project = optionalProject.get();
        }

        Assertions.assertThat(project).isNull();
    }

    @Test
    @Order(7)
    void deleteByCandidateId() {
        Candidate candidate = candidateRepository.findByEmail("candidate@email.com").get();
        projectRepository.deleteByCandidateId(candidate.getId());

        List<Project> projectList = projectRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(projectList.size()).isEqualTo(0);
    }

    @Test
    @Order(8)
    void deleteCandidateById() {
        Candidate candidate = candidateRepository.findByEmail("candidate@email.com").get();
        candidateRepository.deleteById(candidate.getId());

        Candidate candidate1 = null;
        Optional<Candidate> optionalCandidate = candidateRepository.findById(1);

        if(optionalCandidate.isPresent()){
            candidate1 = optionalCandidate.get();
        }

        Assertions.assertThat(candidate1).isNull();
    }

}