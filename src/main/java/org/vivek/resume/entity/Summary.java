package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "summary")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String summaryDesc;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}
