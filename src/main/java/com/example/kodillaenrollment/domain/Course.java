package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "assignedCourses")
    private List<Teacher> assignedTeachers;

    @ManyToMany(mappedBy = "courseList")
    private List<Student> students;

    @Temporal(TemporalType.DATE)
    @Column(name = "starting_date")
    private LocalDate startingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "price_per_month")
    private int pricePerMonth;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;


    @Column(name = "day")
    private String day;

    @Column(name = "time")
    private LocalTime time;

    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Payment> payment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
