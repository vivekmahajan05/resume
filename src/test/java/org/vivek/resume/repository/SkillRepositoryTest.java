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
import org.vivek.resume.entity.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SkillRepositoryTest {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void saveSkillTest(){

        Candidate candidate = new Candidate();
        candidate.setFirstName("Firstname");
        candidate.setLastName("Lastname");
        candidate.setEmail("SkillRepositoryTest@gmail.com");
        candidate.setPhone("1234567890");
        candidate.setTitle("Title");
        candidate.setLinkedinUrl("url");
        candidate.setProfessionalSummary("Professional Summary");

        Candidate savedCandidate = candidateRepository.save(candidate);

        Skill skill = new Skill();
        skill.setSkillType("Test Skill Type");
        skill.setSkillDesc("Test Skill Desc");

        skill.setCandidate(savedCandidate);

        Skill savedSkill = skillRepository.save(skill);
        Assertions.assertThat(savedSkill.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    void saveSkillListTest(){

        Candidate candidate = candidateRepository.findByEmail("SkillRepositoryTest@gmail.com").get();

        List<Skill> skills = new ArrayList<>();
        for(int i=0; i <=5; i++) {

            Skill skill = new Skill();
            skill.setSkillType("Test Skill Type " + i);
            skill.setSkillDesc("Test Skill Desc " + i);

            skill.setCandidate(candidate);
            skills.add(skill);
        }

        skillRepository.saveAll(skills);

        List<Skill> skillList = skillRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(skillList.size()).isGreaterThan(5);
    }

    @Test
    @Order(3)
    void findSkillByCandidateIdTest() {
        Candidate candidate = candidateRepository.findByEmail("SkillRepositoryTest@gmail.com").get();
        Optional<List<Skill>> skillList = skillRepository.findByCandidateId(candidate.getId());

        List<Skill> skills = null;

        if(skillList.isPresent())
            skills = skillList.get();

        Assertions.assertThat(skills).isNotNull();
    }

    @Test
    @Order(4)
    void findSkillBySummaryIdTest(){
        Optional<Skill> optionalSkill = skillRepository.findById(1);

        Skill skill = null;
        if(optionalSkill.isPresent())
            skill = optionalSkill.get();

        Assertions.assertThat(skill).isNotNull();
    }

    @Test
    @Order(5)
    void updateSkillTest(){
        Optional<Skill> optionalSkill = skillRepository.findById(1);

        Skill skill = null;
        if(optionalSkill.isPresent())
            skill = optionalSkill.get();

        skill.setSkillType("Updated Test Skill Type");
        Skill savedSummary = skillRepository.save(skill);

        Assertions.assertThat(savedSummary.getSkillType()).isEqualTo("Updated Test Skill Type");


    }

    @Test
    @Order(6)
    void deleteByProjectId() {
        skillRepository.deleteById(1);

        Skill skill = null;
        Optional<Skill> optionalSkill = skillRepository.findById(1);

        if(optionalSkill.isPresent()){
            skill = optionalSkill.get();
        }

        Assertions.assertThat(skill).isNull();
    }

    @Test
    @Order(7)
    void deleteByCandidateId() {
        Candidate candidate = candidateRepository.findByEmail("SkillRepositoryTest@gmail.com").get();
        skillRepository.deleteByCandidateId(candidate.getId());

        List<Skill> skillList = skillRepository.findByCandidateId(candidate.getId()).get();
        Assertions.assertThat(skillList.size()).isEqualTo(0);
    }

    @Test
    @Order(8)
    void deleteCandidateById() {
        Candidate candidate = candidateRepository.findByEmail("SkillRepositoryTest@gmail.com").get();
        candidateRepository.deleteById(candidate.getId());

        Candidate candidate1 = null;
        Optional<Candidate> optionalCandidate = candidateRepository.findById(1);

        if(optionalCandidate.isPresent()){
            candidate1 = optionalCandidate.get();
        }

        Assertions.assertThat(candidate1).isNull();
    }

}