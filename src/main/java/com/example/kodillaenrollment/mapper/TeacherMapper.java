package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.domain.TeacherDto;

public class TeacherMapper {

    public Teacher mapToTeacher (final TeacherDto teacherDto) {
        return new Teacher(
                teacherDto.getId(),
                teacherDto.getFirstname(),
                teacherDto.getLastname(),
                teacherDto.getAssignedCourses(),
                teacherDto.getDescription()
        );
    }

    public TeacherDto mapToTeacherDto (final Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstname(),
                teacher.getLastname(),
                teacher.getAssignedCourses(),
                teacher.getDescription()
        );
    }
}
