package com.example.kodillaenrollment.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
