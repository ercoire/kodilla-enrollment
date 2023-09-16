package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.StudentDto;
import com.example.kodillaenrollment.repository.CourseRepository;
import com.example.kodillaenrollment.repository.PaymentRepository;
import com.example.kodillaenrollment.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void shouldGetStudents() throws Exception {
        //Given
        Student student1 = new Student(null, "name1", "name2");
        Student student2 = new Student(null, "name3", "name4");

        studentRepository.save(student1);
        studentRepository.save(student2);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/students")
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname", Matchers.is("name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname", Matchers.is("name2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstname", Matchers.is("name3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastname", Matchers.is("name4")));
    }

    @Test
    void shouldGetStudent() throws Exception {
        //Given
        Student student1 = new Student(null, "name1", "name2");
        studentRepository.save(student1);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/students/{studentId}", student1.getId())
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(student1.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("name2")));
    }

    @Test
    void shouldCreateStudent() throws Exception {
        //Given
        StudentDto dto = new StudentDto(null, "firstname", "lastname");
        String jsonContent = objectMapper.writeValueAsString(dto);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        //Given
        Course course = new Course(null, "title", List.of(), List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        courseRepository.save(course);
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);

        Payment payment = new Payment(null, LocalDate.now(), null, 100, course.getId());
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        paymentRepository.save(payment);
        Student student = new Student(null, "name1", "name2", courseList, paymentList);
        studentRepository.save(student);

        StudentDto dto = new StudentDto(student.getId(), "new name1", "new name2");

        String jsonContent = objectMapper.writeValueAsString(dto);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/students/{studentId}", student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Optional<Student> actual = studentRepository.findById(student.getId());
        assertTrue(actual.isPresent());
        Student updated = actual.get();
        assertEquals(1, updated.getPayments().size());
        assertEquals(1, updated.getCourseList().size());
        assertEquals("new name1", updated.getFirstname());
        assertEquals("new name2", updated.getLastname());
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        //Given
        Course course = new Course();
        List<Course> courseList = new ArrayList<>();
        courseRepository.save(course);
        courseList.add(course);

        Payment payment = new Payment();
        paymentRepository.save(payment);
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);

        Student student = new Student(null, "first", "last", courseList, paymentList);
        studentRepository.save(student);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/students/{studentId}", student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));

        assertFalse(studentRepository.findById(student.getId()).isPresent());
        assertTrue(courseRepository.findById(course.getId()).isPresent());
        assertTrue(paymentRepository.findById(payment.getId()).isPresent());
    }

    @Test
    void shouldGetCoursesByStudentId() throws Exception {
        //Given
        Course course1 = new Course(null, "title1", List.of(), List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        Course course2 = new Course(null, "title2", List.of(), List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 9, 30),
                120, "test", 70, "Mon",
                LocalTime.now(), List.of());
        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        courseRepository.save(course1);
        courseRepository.save(course2);

        Student student = new Student(null, "name1", "name2", courseList, List.of());
        studentRepository.save(student);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/students/{studentId}/courses", student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("title2")));
    }

    @Test
    void shouldGetPaymentsByStudentId() throws Exception {
        //Given
        List<Payment> paymentList = new ArrayList<>();
        Student student = new Student(null, "name1", "name2", List.of(), paymentList);
        studentRepository.save(student);

        Payment payment1 = new Payment(null, LocalDate.of(2023, 9, 10), student, 10, null);
        Payment payment2 = new Payment(null, LocalDate.of(2023, 8, 4), student, 50, null);
        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        studentRepository.save(student);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/students/{studentId}/payments", student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].amount", Matchers.is(50)));
    }
}