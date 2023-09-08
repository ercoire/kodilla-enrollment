package com.example.kodillaenrollment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "COURSES")
public class Course {

    @Id
    @Column(name = "id")
    private Long id;
    private String title;
    private String teacher;
    private Date startingDate;
    private Date endDate;
    private int pricePerMonth;
    private String description;
    private int duration;
    private int level;
    private String day;
    private Time time;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
