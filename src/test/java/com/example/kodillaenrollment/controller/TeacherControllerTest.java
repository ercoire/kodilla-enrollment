package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseDto;
import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.domain.TeacherDto;
import com.example.kodillaenrollment.repository.CourseRepository;
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
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateTeacher() throws Exception {
        //Given
        TeacherDto teacherDto = new TeacherDto(
                null, "firstname", "lastname", "dummy");
        String jsonContent = objectMapper.writeValueAsString(teacherDto);

        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetAllTeachers() throws Exception {
        //Given
        Teacher test1 = new Teacher(
                null, "firstname1", "lastname1", "dummy1");
        Teacher test2 = new Teacher(
                null, "firstname2", "lastname2", "dummy2");
        teacherRepository.save(test1);
        teacherRepository.save(test2);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname", Matchers.is("firstname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstname", Matchers.is("firstname2")));
    }

    @Test
    void shouldGetCoursesByTeacherId() throws Exception{
        //Given
        Course course1 = new Course(null, "title1", null, List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        Course course2 = new Course(null, "title2", null, List.of(),
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                130, "test1", 40, "Tue",
                LocalTime.now(), List.of());
        courseRepository.save(course1);
        courseRepository.save(course2);
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        Teacher teacher = new Teacher(
                null, "firstname", "lastname", courses, "dummy");
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher);
        teacherRepository.save(teacher);

        course1.setAssignedTeachers(teacherList);
        course2.setAssignedTeachers(teacherList);
        courseRepository.save(course1);
        courseRepository.save(course2);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/teachers/{teacherId}/courses", teacher.getId().toString())
                .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("title2")));
    }

    @Test
    void shouldGetTeacher() throws Exception {
        //Given
        Teacher test = new Teacher(
                null, "firstname", "lastname", "dummy");
        teacherRepository.save(test);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/teachers/{teacherId}", test.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("firstname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("lastname")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("dummy")));
    }

    @Test
    void shouldUpdateTeacher() throws Exception {
        //Given
        Course course = new Course(null, "title", null, List.of(), LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31),
                100, "test", 70, "Mon",
                LocalTime.now(), List.of());
        courseRepository.save(course);
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        Teacher teacher = new Teacher(
                null, "firstname", "lastname", courses, "dummy");
        teacherRepository.save(teacher);

        TeacherDto teacherDto = new TeacherDto(teacher.getId(), "new firstname", "new lastname", "new dummy");
        String jsonContent = objectMapper.writeValueAsString(teacherDto);

        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/teachers/{teacherId}", teacher.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Optional<Teacher> actual = teacherRepository.findById(teacher.getId());
        assertTrue(actual.isPresent());
        Teacher updatedTeacher = actual.get();
        assertEquals(1, updatedTeacher.getAssignedCourses().size());
        assertEquals(updatedTeacher.getFirstname(), teacherDto.getFirstname());
        assertEquals(updatedTeacher.getLastname(), teacherDto.getLastname());
        assertEquals(updatedTeacher.getDescription(), teacherDto.getDescription());
    }

    @Test
    void shouldDeleteTeacher() throws Exception {
        //Given
        Course course = new Course();
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        courseRepository.save(course);
        Teacher test = new Teacher(null, "name1", "name2", courseList, "test");

        teacherRepository.save(test);

        //When-Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/teachers/{teacherId}", test.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));

        assertFalse(teacherRepository.findById(test.getId()).isPresent());
        assertTrue(courseRepository.findById(course.getId()).isPresent());
    }


}