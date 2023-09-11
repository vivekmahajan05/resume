package org.vivek.resume.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Project;
import org.vivek.resume.entity.ProjectResponsibility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectResponsibilityRepositoryTest {

    @Autowired
    private ProjectResponsibilityRepository projectResponsibilityRepository;

    @Autowired
    private  ProjectRepository projectRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveProjectResponsibilityTest(){

        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("ProjectResponsibilityTest@gmail.com");
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

        ProjectResponsibility responsibility = new ProjectResponsibility();
        responsibility.setPrDesc("Test Project Responsibility");
        responsibility.setProject(savedProject);
        responsibility.setCandidate(savedCandidate);

        ProjectResponsibility savedProjectResponsibility = projectResponsibilityRepository.save(responsibility);
        Assertions.assertThat(savedProjectResponsibility.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void saveProjectListTest(){

        Candidate candidate = candidateRepository.findByEmail("ProjectResponsibilityTest@gmail.com").get();
        //Project project = projectRepository.findByCandidateId(candidate.getId());

        List<ProjectResponsibility> responsibilities = new ArrayList<>();
        for(int i=1; i <=5; i++) {

            ProjectResponsibility projectResponsibility = new ProjectResponsibility();
            projectResponsibility.setPrDesc("Test Project Desc");

            projectResponsibility.setCandidate(candidate);
            responsibilities.add(projectResponsibility);
        }

        projectResponsibilityRepository.saveAll(responsibilities);

        List<ProjectResponsibility> projectResponsibilities = projectResponsibilityRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(projectResponsibilities.size()).isEqualTo(6);
    }

    @Test
    @Order(3)
    void findProjectByCandidateIdTest() {
        Candidate candidate = candidateRepository.findByEmail("ProjectResponsibilityTest@gmail.com").get();
        Optional<List<ProjectResponsibility>> optionalResponsibilities = projectResponsibilityRepository.findByCandidateId(candidate.getId());

        List<ProjectResponsibility> responsibilities = null;

        if(optionalResponsibilities.isPresent())
            responsibilities = optionalResponsibilities.get();

        Assertions.assertThat(responsibilities).isNotNull();
    }

    @Test
    @Order(4)
    void findProjectByProjectIdTest(){
        Optional<ProjectResponsibility> optionalProjectResponsibility = projectResponsibilityRepository.findById(1);

        ProjectResponsibility responsibility = null;
        if(optionalProjectResponsibility.isPresent())
            responsibility = optionalProjectResponsibility.get();

        Assertions.assertThat(responsibility).isNotNull();
    }

    @Test
    @Order(5)
    void updateProjectTest(){
        Optional<ProjectResponsibility> optionalProjectResponsibility = projectResponsibilityRepository.findById(1);

        ProjectResponsibility projectResponsibility = null;
        if(optionalProjectResponsibility.isPresent())
            projectResponsibility = optionalProjectResponsibility.get();

        projectResponsibility.setPrDesc("Updated Test Project Desc");
        ProjectResponsibility savedResponsibility = projectResponsibilityRepository.save(projectResponsibility);

        Assertions.assertThat(savedResponsibility.getPrDesc()).isEqualTo("Updated Test Project Desc");


    }

    @Test
    @Order(6)
    void deleteByProjectId() {
        projectResponsibilityRepository.deleteById(1);

        ProjectResponsibility projectResponsibility = null;
        Optional<ProjectResponsibility> optionalProjectResponsibility = projectResponsibilityRepository.findById(1);

        if(optionalProjectResponsibility.isPresent()){
            projectResponsibility = optionalProjectResponsibility.get();
        }

        Assertions.assertThat(projectResponsibility).isNull();
    }

    @Test
    @Order(7)
    void deleteByCandidateId() {
        Candidate candidate = candidateRepository.findByEmail("ProjectResponsibilityTest@gmail.com").get();
        projectResponsibilityRepository.deleteByCandidateId(candidate.getId());

        List<ProjectResponsibility> responsibilities = projectResponsibilityRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(responsibilities.size()).isEqualTo(0);
    }

    @Test
    @Order(8)
    void deleteCandidateById() {
        Candidate candidate = candidateRepository.findByEmail("ProjectResponsibilityTest@gmail.com").get();
        candidateRepository.deleteById(candidate.getId());

        Candidate candidate1 = null;
        Optional<Candidate> optionalCandidate = candidateRepository.findById(1);

        if(optionalCandidate.isPresent()){
            candidate1 = optionalCandidate.get();
        }

        Assertions.assertThat(candidate1).isNull();
    }

    @Test
    void findByCandidateId() {
    }

    @Test
    void findByProjectId() {
    }

    @Test
    void deleteByCandidateIdTest() {
    }

    @Test
    void deleteByProjectIdTest() {
    }
}