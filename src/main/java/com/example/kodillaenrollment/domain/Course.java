package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Entity(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "COURSES_TO_TEACHERS",
            joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")}
    )
    private List<Teacher> assignedTeachers = new ArrayList<>();

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

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "course_id")
    private List<Payment> payment = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
