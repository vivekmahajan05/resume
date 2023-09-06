package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vivek.resume.entity.Candidate;
import org.vivek.resume.entity.Skill;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.resume.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkillService {

    private final SkillRepository skillRepository;

    public Skill save(Integer candidateId, Skill skill){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        skill.setCandidate(candidate);
        return skillRepository.save(skill);
    }

    public void saveAll(Integer candidateId, List<Skill> skills){
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);

        skills.forEach(skill -> skill.setCandidate(candidate));
        skillRepository.saveAll(skills);
    }

    public Skill updateById(Integer skillId, Skill skill){
        skill.setId(skillId);
        return skillRepository.save(skill);
    }

    public Skill getById(Integer skillId){
        Optional<Skill> skill = Optional.of(skillRepository.findById(skillId).orElseThrow(NotFoundException::new));
        return skill.get();
    }

    public List<Skill> getByCandidateId(Integer candidateId){
        Optional<List<Skill>> skillList = Optional.of(skillRepository.findByCandidateId(candidateId).orElseThrow(NotFoundException::new));
        return skillList.get();
    }

    public void deleteById(Integer skillId){
        skillRepository.deleteById(skillId);
    }

    public void deleteByCandidateId(Integer candidateId){
        skillRepository.deleteByCandidateId(candidateId);
    }
}
