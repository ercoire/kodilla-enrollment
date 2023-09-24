package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.PaymentCreationDto;
import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.repository.CourseRepository;
import com.example.kodillaenrollment.repository.PaymentRepository;
import com.example.kodillaenrollment.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    void shouldCreatePayment() throws Exception {
        //Given
        Course course = new Course(null, "title", null, null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 2, 20),
                50, "dummy", 50, "Tue", LocalTime.now(),
                new ArrayList<>());
        courseRepository.save(course);
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);

        Student student = new Student(null, "first", "last", "mail", courseList, new ArrayList<>());
        studentRepository.save(student);

        PaymentCreationDto dto = new PaymentCreationDto(null, LocalDate.now(), student.getId(), 50, course.getId());
        String jsonContent = objectMapper.writeValueAsString(dto);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size());
        Payment actual = payments.get(0);
        assertEquals(course.getId(), actual.getCourseId());
        assertEquals(student.getId(), actual.getStudent().getId());

        Optional<Student> resultStudent = studentRepository.findById(student.getId());
        assertTrue(resultStudent.isPresent());
        assertEquals(resultStudent.get().getPayments().get(0).getId(), actual.getId());

        Optional<Course> resultCourse = courseRepository.findById(course.getId());
        assertTrue(resultCourse.isPresent());
        assertEquals(resultCourse.get().getPayment().get(0).getId(), actual.getId());
    }

}