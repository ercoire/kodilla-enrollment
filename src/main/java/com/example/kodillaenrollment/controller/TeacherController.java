package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseDto;
import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.domain.TeacherDto;
import com.example.kodillaenrollment.mapper.CourseMapper;
import com.example.kodillaenrollment.mapper.TeacherMapper;
import com.example.kodillaenrollment.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teachers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        return ResponseEntity.ok(teacherMapper.mapToTeacherDtoList(teacherList));
    }

    @GetMapping(value = "/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacher(teacherId);
        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(teacher));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTeacher(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.mapToTeacher(teacherDto);
        teacherService.saveTeacher(teacher);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{teacherId}") //todo techarID
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Long teacherId) {
        Teacher teacher = teacherMapper.mapToTeacher(teacherDto);
        Teacher savedTeacher = teacherService.updateTeacher(teacher);
        return ResponseEntity.ok(teacherMapper.mapToTeacherDto(savedTeacher));
    }

    @GetMapping(value = "/{teacherId}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesByTeacher(@PathVariable Long teacherId) {
      List<Course> coursesByTeacher = teacherService.getCoursesByTeacher(teacherId);
        return ResponseEntity.ok(courseMapper.mapToCourseDtoList(coursesByTeacher));
    }

    @DeleteMapping(value = "/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }

}
