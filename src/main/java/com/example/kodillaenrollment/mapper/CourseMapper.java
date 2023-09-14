package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseMapper {

    @Autowired
    private TeacherMapper teacherMapper;

    public Course mapToCourse(final CourseDto courseDto) {
        return new Course(
                courseDto.getId(),
                courseDto.getTitle(),
                teacherMapper.mapToTeacherList(courseDto.getAssignedTeachers()),
                List.of(),
                courseDto.getStartingDate(),
                courseDto.getEndDate(),
                courseDto.getPricePerMonth(),
                courseDto.getDescription(),
                courseDto.getDuration(),
                courseDto.getDay(),
                courseDto.getTime(),
                List.of()
        );
    }

    public CourseDto mapToCourseDto(final Course course) {
        return new CourseDto(
                course.getId(),
                course.getTitle(),
                teacherMapper.mapToTeacherDtoList(course.getAssignedTeachers()),
                course.getStartingDate(),
                course.getEndDate(),
                course.getPricePerMonth(),
                course.getDescription(),
                course.getDuration(),
                course.getDay(),
                course.getTime()
        );
    }

    public List<CourseDto> mapToCourseDtoList(final List<Course> courseList) {
        return courseList.stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }
}
