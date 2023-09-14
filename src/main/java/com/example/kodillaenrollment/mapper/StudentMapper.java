package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper {

    public Student mapToStudent(final StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getFirstname(),
                studentDto.getLastname(),
                studentDto.getCourseList(),
                studentDto.getPayments(),
          //      studentDto.getAbsences(),
                studentDto.getEventAttendance()
        );
    }

    public StudentDto mapToStudentDto(final Student student) {
        return new StudentDto(
//                student.getId(),
//                student.getFirstname(),
//                student.getLastname(),
//                student.getCourseList(),
//                student.getPaymentDate(),
//                student.getPaymentAmount(),
//                student.getAbsences(),
//                student.getEventAttendance()
                );
    }

    public StudentDto mapToBasicStudentDto(final Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstname(),
                student.getLastname()
        );
    }

    public Student mapToBasicStudent(final StudentDto studentDto){
        return new Student(
                studentDto.getId(),
                studentDto.getFirstname(),
                studentDto.getLastname()
        );
    }

    public List<StudentDto> mapToStudentDtoList(final List<Student> studentList) {
        return studentList.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }
}
