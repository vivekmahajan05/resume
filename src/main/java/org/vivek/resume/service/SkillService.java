package org.vivek.resume.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public Skill save(Skill skill){
        return skillRepository.save(skill);
    }

    public void saveAll(List<Skill> skills){
        skillRepository.saveAll(skills);
    }

    public Skill getById(Integer id){
        Optional<Skill> skill = Optional.of(skillRepository.findById(id).orElseThrow(NotFoundException::new));
        return skill.get();
    }

    public List<Skill> getByCandidateId(Integer id){
        Optional<List<Skill>> skillList = Optional.of(skillRepository.findByCandidateId(id).orElseThrow(NotFoundException::new));
        return skillList.get();
    }

    public void deleteById(Integer id){
        skillRepository.deleteById(id);
    }

    public void deleteByCandidateID(Integer id){
        skillRepository.deleteByCandidateId(id);
    }
}
