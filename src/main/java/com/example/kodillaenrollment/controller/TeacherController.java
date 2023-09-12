package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseDto;
import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.domain.TeacherDto;
import com.example.kodillaenrollment.mapper.CourseMapper;
import com.example.kodillaenrollment.mapper.TeacherMapper;
import com.example.kodillaenrollment.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final DbService dbService;
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        List<Teacher> teacherList = dbService.getAllTeachers();
        return ResponseEntity.ok(teacherMapper.mapToTeacherDtoList(teacherList));
    }

    @GetMapping(value = "{teacherId}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long teacherId) {
        Teacher teacher = dbService.getTeacher(teacherId);
        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(teacher));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTeacher(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.mapToTeacher(teacherDto);
        dbService.saveTeacher(teacher);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.mapToTeacher(teacherDto);
        Teacher savedTeacher = dbService.saveTeacher(teacher);
        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(savedTeacher));
    }

    @GetMapping(value = "{teacherId}/courses")
    public ResponseEntity<List<CourseDto>> fetchCoursesByTeacher(@PathVariable String teacherId) {
        List<Course> coursesByTeacher = dbService.getAllCourses().stream()
                .filter(course -> course.getAssignedTeachers() != null &&
                        course.getAssignedTeachers().stream()
                                .anyMatch(teacher -> teacher.getId().equals(Long.parseLong(teacherId))))
                .toList();
        return ResponseEntity.ok(courseMapper.mapToCourseDtoList(coursesByTeacher));
    }

    @DeleteMapping(value = "{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        dbService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }

}
