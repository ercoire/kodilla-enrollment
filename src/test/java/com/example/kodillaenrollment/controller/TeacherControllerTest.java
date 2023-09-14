package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.domain.TeacherDto;
import com.example.kodillaenrollment.repository.TeacherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

}