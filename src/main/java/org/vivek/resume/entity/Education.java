package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String college;

    @Column(columnDefinition = "TEXT")
    private String university;

    @Column(columnDefinition = "TEXT")
    private String specialization;

    @Column(columnDefinition = "TEXT")
    private String degree;

    @JsonFormat(pattern="yyyy")
    private Date graduationYear;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "educations")
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "Candidate_id")
    private Candidate candidate;

}
