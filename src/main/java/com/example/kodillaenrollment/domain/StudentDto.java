package com.example.kodillaenrollment.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class StudentDto {
    private Long id;
    private String firstname;
    private String lastname;
//    private List<Course> courseList;
//    private List<Payment> payments;
//    private List<Event> eventAttendance;


}
