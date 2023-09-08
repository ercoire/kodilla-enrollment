package com.example.kodillaenrollment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "STUDENTS")
public class Student {

    @Id
    @Column(name = "id")
    private Long id;

    private String firstname;
    private String lastname;
    private String activeCourses;
    private String futureCourses;
    private String payments;
    private Date absences;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
