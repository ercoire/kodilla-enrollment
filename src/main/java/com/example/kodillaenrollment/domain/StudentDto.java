package com.example.kodillaenrollment.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentDto {
    private Long id;
    private String firstname;
    private String lastname;
    private List<Course> courseList;
    private List<Payment> payments;
    //private Date absences;
    private List<Event> eventAttendance;

    public StudentDto(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
