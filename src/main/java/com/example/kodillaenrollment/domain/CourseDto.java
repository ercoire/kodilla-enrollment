package com.example.kodillaenrollment.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class CourseDto {

    private Long id;
    private String title;
    private List<TeacherDto> assignedTeachers;
    private Date startingDate;
    private Date endDate;
    private int pricePerMonth;
    private String description;
    private int duration;
    private int level;
    private String day;
    private Time time;
}
