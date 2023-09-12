package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseDto;
import com.example.kodillaenrollment.mapper.CourseMapper;
import com.example.kodillaenrollment.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final DbService dbService;
    private final CourseMapper courseMapper;



    @GetMapping(value = "{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long courseId) {
        Course course = dbService.getCourse(courseId);
        return ResponseEntity.ok(courseMapper.mapToCourseDto(course));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses() {
        List<Course> courseList = dbService.getAllCourses();
        return ResponseEntity.ok(courseMapper.mapToCourseDtoList(courseList));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCourse(@RequestBody CourseDto courseDto) {
        Course course = courseMapper.mapToCourse(courseDto);
        dbService.saveCourse(course);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        Course course = courseMapper.mapToCourse(courseDto);
        Course savedCourse = dbService.saveCourse(course);
        return ResponseEntity.ok(courseMapper.mapToCourseDto(savedCourse));
    }

    @DeleteMapping(value = "{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        dbService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }

}

