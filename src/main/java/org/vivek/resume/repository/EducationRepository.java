package org.vivek.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.resume.entity.Education;

public interface EducationRepository extends JpaRepository<Education,Integer> {
}
