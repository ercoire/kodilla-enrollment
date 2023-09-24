package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.*;
import com.example.kodillaenrollment.mapper.CourseMapper;
import com.example.kodillaenrollment.mapper.PaymentMapper;
import com.example.kodillaenrollment.mapper.StudentMapper;
import com.example.kodillaenrollment.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/courses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;
    private final PaymentMapper paymentMapper;

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long courseId) {
        Course course = courseService.getCourse(courseId);
        return ResponseEntity.ok(courseMapper.mapToCourseDto(course));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses() {
        List<Course> courseList = courseService.getAllCourses();
        return ResponseEntity.ok(courseMapper.mapToCourseDtoList(courseList));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCourse(@RequestBody CourseDto courseDto) {
        Course course = courseMapper.mapToCourse(courseDto);
        courseService.saveCourse(course);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{courseId}") //todo courseid
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        Course course = courseMapper.mapToCourse(courseDto);
        Course savedCourse = courseService.updateCourse(course);
        return ResponseEntity.ok(courseMapper.mapToCourseDto(savedCourse));
    }

    @DeleteMapping(value = "/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{courseId}/payments")
    public ResponseEntity<List<PaymentDto>> getPaymentsByCourseId(@PathVariable Long courseId){
        Course course = courseService.getCourse(courseId);
        List<Payment> paymentsByCourse = course.getPayment();
        return ResponseEntity.ok(paymentMapper.mapToPaymentDtoList(paymentsByCourse));
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDto>> getStudentsByCourseId(@PathVariable Long courseId){
        Course course = courseService.getCourse(courseId);
        List<Student> studentList = course.getStudents();
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(studentList));
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Void> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId){
        courseService.addStudentToCourse(courseId, studentId);
        return ResponseEntity.ok().build();
    }
}

