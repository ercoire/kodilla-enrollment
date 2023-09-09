package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.StudentDto;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student mapToStudent(final StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getFirstname(),
                studentDto.getLastname(),
                studentDto.getActiveCourses(),
                studentDto.getFutureCourses(),
                studentDto.getPaymentDate(),
                studentDto.getPaymentAmount(),
                studentDto.getAbsences()
        );
    }

    public StudentDto mapToStudentDto(final Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstname(),
                student.getLastname(),
                student.getActiveCourses(),
                student.getFutureCourses(),
                student.getPaymentDate(),
                student.getPaymentAmount(),
                student.getAbsences());
    }
}
