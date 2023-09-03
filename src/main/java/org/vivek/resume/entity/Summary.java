package org.vivek.resume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "summary")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String summaryDesc;

    @JsonBackReference
    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Summary summary)) return false;
        return getId() == summary.getId() && Objects.equals(getSummaryDesc(), summary.getSummaryDesc()) && Objects.equals(getCandidate(), summary.getCandidate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSummaryDesc());
    }


}
