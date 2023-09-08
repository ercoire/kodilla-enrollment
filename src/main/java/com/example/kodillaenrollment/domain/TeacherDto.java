package com.example.kodillaenrollment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TeacherDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String assignedCourses;
    private String description;
}
