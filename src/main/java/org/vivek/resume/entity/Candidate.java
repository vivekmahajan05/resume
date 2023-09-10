package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "first_name" , nullable = false)
    private String firstName;

    @Column(name= "middle_name")
    private String middleName;

    @Column(name= "last_name" ,nullable = false)
    private String lastName;

    @Column(name= "title")
    private String title;

    @Email
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name= "phone", nullable = false)
    private String phone;

    @Column(name= "linkedin_url")
    private String linkedinUrl;

    @Column(name = "professional_summary", nullable = false, columnDefinition = "LONGTEXT")
    private String professionalSummary;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "summaries")
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Summary> summaries = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "educations")
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.ALL, CascadeType.MERGE})
    private List<Education> educations = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "certifications")
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.ALL})
    private List<Certification> certifications = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "skills")
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.ALL})
    private List<Skill> skills = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "projects")
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.ALL})
    private List<Project> projects = new ArrayList<>();

    public void addSummaries(List<Summary> summaries){
        summaries.forEach(summary -> summary.setCandidate(this));
        this.summaries = summaries;
    }

    public void addEducations(List<Education> educations){
        educations.forEach(education -> education.setCandidate(this));
        this.educations = educations;
    }

    public void addCertifications(List<Certification> certifications){
        certifications.forEach(certification -> certification.setCandidate(this));
        this.certifications = certifications;
    }

    public void addSkills(List<Skill> skills){
        skills.forEach(skill -> skill.setCandidate(this));
        this.skills = skills;
    }

    public void addProjects(List<Project> projects){
        for (Project project: projects) {
            project.setCandidate(this);
            for( ProjectResponsibility projectResponsibility: project.getProjectResponsibilities()){
                projectResponsibility.setCandidate(this);
            }
        }
        this.projects = projects;
    }
}
