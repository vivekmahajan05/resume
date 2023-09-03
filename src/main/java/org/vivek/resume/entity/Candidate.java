package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;


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
    @Column(name="email", nullable = false)
    private String email;

    @Column(name= "phone", nullable = false)
    private String phone;

    @Column(name= "linkedin_url")
    private String linkedinUrl;

    @Column(name = "professional_summary", nullable = false, columnDefinition = "LONGTEXT")
    private String professionalSummary;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Set<Summary> summaries = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "candidate", cascade = { CascadeType.ALL, CascadeType.MERGE})
    private Set<Education> educations = new HashSet<>();

    public void addSummaries(Set<Summary> summaries){
        summaries.forEach(summary -> summary.setCandidate(this));
        this.summaries = summaries;
    }

    public void addEducations(Set<Education> educations){
        educations.forEach(education -> education.setCandidate(this));
        this.educations = educations;
    }
}
