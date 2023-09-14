package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.StudentDto;
import com.example.kodillaenrollment.mapper.StudentMapper;
import com.example.kodillaenrollment.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final DbService dbService;
    private final StudentMapper studentMapper;


    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<Student> studentList = dbService.getAllStudents();
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(studentList));
    }

    @GetMapping(value = "{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId) {
        Student student = dbService.getStudent(studentId);
        return ResponseEntity.ok(studentMapper.mapToStudentDto(student));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createStudent(@RequestBody StudentDto studentDto) {
        Student student = studentMapper.mapToBasicStudent(studentDto);
        dbService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
        Student student = studentMapper.mapToStudent(studentDto);
        Student savedStudent = dbService.saveStudent(student);
        return ResponseEntity.ok(studentMapper.mapToStudentDto(savedStudent));
    }

    @DeleteMapping(value = "{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
        dbService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
