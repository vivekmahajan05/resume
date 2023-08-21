package org.vivek.resume.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.Email;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "firstName" , nullable = false)
    private String firstName;

    @Column(name= "middleName")
    private String middleName;

    @Column(name= "lastName" ,nullable = false)
    private String lastName;

    @Column(name= "title")
    private String title;

    @Email
    @Column(name="email", nullable = false)
    private String email;

    @Column(name= "phone", nullable = false)
    private String phone;

    @Column(name= "linkedinUrl")
    private String linkedinUrl;

    @Column(name = "ProfessionalSummary", nullable = false, columnDefinition = "LONGTEXT")
    private String professionalSummary;
}
