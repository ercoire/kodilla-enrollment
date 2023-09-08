package com.example.kodillaenrollment.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CourseDto {

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
}
