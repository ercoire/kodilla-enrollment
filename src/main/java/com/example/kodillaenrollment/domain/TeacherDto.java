package com.example.kodillaenrollment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TeacherDto {

    private Long id;
    private String firstname;
    private String lastname;
    private List<Course> assignedCourses;
    private String description;
}
