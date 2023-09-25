package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity(name = "COURSE_ENROLLMENT_NOTIFICATION")
public class CourseEnrollmentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Column(name = "COURSE")
    private String courseTitle;

    public CourseEnrollmentNotification(String email, String studentName, String courseTitle) {
        this.email = email;
        this.studentName = studentName;
        this.courseTitle = courseTitle;
    }


}
