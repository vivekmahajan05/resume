package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

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

    @JsonFormat(pattern="yyyy-MM")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM")
    private Date endDate;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isPresent;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "projectResponsibilities")
    @OneToMany(mappedBy = "project", cascade = { CascadeType.ALL})
    private List<ProjectResponsibility> projectResponsibilities = new ArrayList<>();


    private String projectSkill;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "projects")
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "Candidate_id")
    private Candidate candidate;

    public void addProjectResponsibilities(List<ProjectResponsibility> projectResponsibilities){
        projectResponsibilities.forEach(projectResponsibility -> projectResponsibility.setProject(this));
        this.projectResponsibilities = projectResponsibilities;

    }
}
