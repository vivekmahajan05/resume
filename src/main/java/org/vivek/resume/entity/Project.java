package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String projectName;
    private String company;
    private String location;
    private String role;
    private String duration;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "projectResponsibilities")
    @OneToMany(mappedBy = "project", cascade = { CascadeType.ALL})
    private Set<ProjectResponsibility> projectResponsibilities = new HashSet<>();


    private String projectSkill;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "projects")
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "Candidate_id")
    private Candidate candidate;

    public void addProjectResponsibilities(Set<ProjectResponsibility> projectResponsibilities){
        projectResponsibilities.forEach(projectResponsibility -> projectResponsibility.setProject(this));
        this.projectResponsibilities = projectResponsibilities;
    }
}
