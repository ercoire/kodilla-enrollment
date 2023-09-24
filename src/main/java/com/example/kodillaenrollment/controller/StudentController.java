package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.*;
import com.example.kodillaenrollment.mapper.CourseMapper;
import com.example.kodillaenrollment.mapper.PaymentMapper;
import com.example.kodillaenrollment.mapper.StudentMapper;
import com.example.kodillaenrollment.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final PaymentMapper paymentMapper;


    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(studentList));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudent(studentId);
        return ResponseEntity.ok(studentMapper.mapToStudentDto(student));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createStudent(@RequestBody StudentDto studentDto) {
        Student student = studentMapper.mapToStudent(studentDto);
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{studentId}") //todo studentid
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
        Student student = studentMapper.mapToStudent(studentDto);
        Student savedStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(studentMapper.mapToStudentDto(savedStudent));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesByStudentId(@PathVariable Long studentId) {
        List<Course> coursesByStudentId = studentService.getCoursesByStudentId(studentId);
        return ResponseEntity.ok(courseMapper.mapToCourseDtoList(coursesByStudentId));
    }

    @GetMapping("/{studentId}/payments")
    public ResponseEntity<List<PaymentDto>> getPaymentsByStudentId(@PathVariable Long studentId) {
        List<Payment> paymentsByStudentId = studentService.getPaymentsByStudentId(studentId);
        return ResponseEntity.ok(paymentMapper.mapToPaymentDtoList(paymentsByStudentId));
    }
}
