package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseDto;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

    public Course mapToCourse(final CourseDto courseDto) {
        return new Course(
                courseDto.getId(),
                courseDto.getTitle(),
                courseDto.getTeacher(),
                courseDto.getStartingDate(),
                courseDto.getEndDate(),
                courseDto.getPricePerMonth(),
                courseDto.getDescription(),
                courseDto.getDuration(),
                courseDto.getLevel(),
                courseDto.getDay(),
                courseDto.getTime()
        );
    }

    public CourseDto mapToGroupDto(final Course course) {
        return new CourseDto(
                course.getId(),
                course.getTitle(),
                course.getTeacher(),
                course.getStartingDate(),
                course.getEndDate(),
                course.getPricePerMonth(),
                course.getDescription(),
                course.getDuration(),
                course.getLevel(),
                course.getDay(),
                course.getTime()
        );
    }
}
