package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "project_responsibility")
public class ProjectResponsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String prDesc;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "candidate")
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "project_candidate_id")
    private Candidate candidate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "projectResponsibilities")
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "project_id")
    private Project project;
}
