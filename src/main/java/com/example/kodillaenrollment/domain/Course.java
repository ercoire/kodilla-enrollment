package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
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

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @Column(name = "starting_date")
    private Date startingDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "price_per_month")
    private int pricePerMonth;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "level")
    private int level;

    @Column(name = "day")
    private String day;

    @Column(name = "time")
    private Time time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
