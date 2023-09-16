package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.*;
import com.example.kodillaenrollment.repository.CourseRepository;
import com.example.kodillaenrollment.repository.PaymentRepository;
import com.example.kodillaenrollment.repository.StudentRepository;
import com.example.kodillaenrollment.repository.TeacherRepository;
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
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    void shouldCreateCourse() throws Exception {
        //Given
        CourseDto dto = new CourseDto(1L, "title", List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now());
        String jsonContent = objectMapper.writeValueAsString(dto);

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetAllCourses() throws Exception {
        //Given
        Course course1 = new Course(null, "title", List.of(), List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        Course course2 = new Course(null, "title2", List.of(), List.of(),
                LocalDate.of(2022, 5, 6),
                LocalDate.of(2022, 10, 31),
                120, "test2", 40, "Tue",
                LocalTime.now(), List.of());
        courseRepository.save(course1);
        courseRepository.save(course2);


        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("title2")));

    }

    @Test
    void shouldGetCourse() throws Exception {
        //Given
        Teacher t = new Teacher(null, "name1", "name2", null, "test");
        List<Teacher> test = new ArrayList<>();
        test.add(t);
        teacherRepository.save(t);
        Course course1 = new Course(null, "title", test, List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        courseRepository.save(course1);


        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/courses/{courseId}", course1.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignedTeachers", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignedTeachers[0].firstname", Matchers.is("name1")));
    }

    @Test
    void shouldDeleteCourse() throws Exception {
        //Given
        Teacher t = new Teacher(null, "name1", "name2", null, "test");
        List<Teacher> test = new ArrayList<>();
        test.add(t);
        teacherRepository.save(t);

        Course course1 = new Course(null, "title", test, List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        courseRepository.save(course1);


        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/courses/{courseId}", course1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));


        assertFalse(courseRepository.findById(course1.getId()).isPresent());
        assertTrue(teacherRepository.findById(t.getId()).isPresent());
    }

    @Test
    void shouldUpdateCourse() throws Exception {
        //Given
        Teacher teacher1 = new Teacher(null, "name1", "name2", null, "test");
        List<Teacher> test = new ArrayList<>();
        test.add(teacher1);
        teacherRepository.save(teacher1);

        Student basicStudent = new Student(null, "studentName", "studentFamily");
        studentRepository.save(basicStudent);
        List<Student> students = new ArrayList<>();
        students.add(basicStudent);

        Course course1 = new Course(null, "title", test, students,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        courseRepository.save(course1);

        Teacher teacher2 = new Teacher(null, "new name 1", "new name 2", null, "test2");
        teacherRepository.save(teacher2);

        TeacherDto teacherDto = new TeacherDto(teacher2.getId(), "name1", "name2", "test");
        List<TeacherDto> testDtoList = new ArrayList<>();
        testDtoList.add(teacherDto);
        CourseDto dto = new CourseDto(course1.getId(), "title", testDtoList,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 6, 30),
                150, "test", 70, "Mon",
                LocalTime.now());
        String jsonContent = objectMapper.writeValueAsString(dto);

        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/courses/{courseId}", course1.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Optional<Course> actual = courseRepository.findById(course1.getId());
        assertTrue(actual.isPresent());
        Course updatedCourse = actual.get();
        assertEquals(updatedCourse.getAssignedTeachers().get(0).getFirstname(), teacher2.getFirstname());
        assertEquals(updatedCourse.getAssignedTeachers().get(0).getLastname(), teacher2.getLastname());
        assertEquals(updatedCourse.getEndDate(), dto.getEndDate());
        assertEquals(updatedCourse.getPricePerMonth(), dto.getPricePerMonth());
        assertEquals(1, updatedCourse.getStudents().size());


    }

    @Test
    void shouldGetPaymentsByCourseId() throws Exception {
        //Given
        Student student = new Student();
        Payment payment1 = new Payment(null, LocalDate.of(2023, 9, 10), student, 10, null);
        Payment payment2 = new Payment(null, LocalDate.of(2023, 8, 4), student, 50, null);
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        Course course = new Course(null, "title", List.of(), List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), paymentList);
        courseRepository.save(course);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/courses/{courseId}/payments", course.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].amount", Matchers.is(50)));
    }

    @Test
    void shouldGetStudentsByCourseId() throws Exception {
        //Given
        Student student1 = new Student(null, "first1", "last1", List.of(), null);
        Student student2 = new Student(null, "first2", "last2", List.of(), null);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        studentRepository.save(student1);
        studentRepository.save(student2);

        Course course = new Course(null, "title", List.of(), students,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), null);
        courseRepository.save(course);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/courses/{courseId}/students", course.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname", Matchers.is("last1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastname", Matchers.is("last2")));
    }

    @Test
    void shouldAddStudentToCourse() throws Exception {
        //Given
        Course course = new Course(null, "title", null, new ArrayList<>(),
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 2, 20),
                50, "dummy", 50, "Tue", LocalTime.now(),
                new ArrayList<>());
        courseRepository.save(course);

        Student student = new Student(null, "first", "last", new ArrayList<>(), new ArrayList<>());
        studentRepository.save(student);


        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/courses/{courseId}/students/{studentId}", course.getId(), student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));


        Optional<Student> resultStudent = studentRepository.findById(student.getId());
        assertTrue(resultStudent.isPresent());
        assertEquals(resultStudent.get().getCourseList().get(0).getId(), course.getId());

        Optional<Course> resultCourse = courseRepository.findById(course.getId());
        assertTrue(resultCourse.isPresent());
        assertEquals(resultCourse.get().getStudents().get(0).getId(), student.getId());
    }
}

