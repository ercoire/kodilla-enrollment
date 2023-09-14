package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.StudentDto;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void shouldCreateStudent() throws Exception {
        //Given
        StudentDto dto = new StudentDto(1L, "firstname", "lastname");
        String jsonContent = objectMapper.writeValueAsString(dto);

        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));

        //Then
        assertThat(dto.getId()).isEqualTo(1L);

    }

//    @Test
//    void shouldUpdateStudent() throws Exception {
//        //Given
//        StudentDto dto = new StudentDto(1L, "firstname", "lastname", List.of(), List.of(), List.of());
    }