package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@Entity
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private Date aquiredOn;

    private String version;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "certifications")
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "Candidate_id")
    private Candidate candidate;
}
