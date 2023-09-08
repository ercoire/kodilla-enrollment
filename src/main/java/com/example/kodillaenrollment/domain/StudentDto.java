package com.example.kodillaenrollment.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class StudentDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String activeCourses;
    private String futureCourses;
    private Date paymentDate;
    private int paymentAmount;
    private Date absences;
}
