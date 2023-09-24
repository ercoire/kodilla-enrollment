package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_TO_COURSE",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private List<Course> courseList = new ArrayList<>();

    @OneToMany (targetEntity = Payment.class,
    mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Payment> payments = new ArrayList<>();


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Student(Long id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
