package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String skillType;

    private String skillDesc;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "skills")
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "Candidate_id")
    private Candidate candidate;
}
