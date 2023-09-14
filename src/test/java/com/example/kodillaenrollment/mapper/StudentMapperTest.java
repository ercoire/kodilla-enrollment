package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.StudentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    void shouldMapToBasicStudentDto() {
        //Given
        Student test = new Student(1L, "firstname", "lastname");

        //When
        StudentDto actual = studentMapper.mapToBasicStudentDto(test);

        //Then
        assertEquals(test.getId(), actual.getId());
        assertEquals(test.getFirstname(), actual.getFirstname());
        assertEquals(test.getLastname(), actual.getLastname());
    }

    @Test
    void shouldMapToBasicStudent() {
        //Given
        StudentDto test = new StudentDto(1L, "firstname", "lastname");

        //When
        Student actual = studentMapper.mapToBasicStudent(test);

        //Then
        assertEquals(test.getId(), actual.getId());
        assertEquals(test.getFirstname(), actual.getFirstname());
        assertEquals(test.getLastname(), actual.getLastname());
    }

    @Test
    void shouldMapToStudent(){
        //Given
        StudentDto dto = new StudentDto(1L, "firstname", "lastname", List.of(), List.of(), List.of());

        //When
        Student actual = studentMapper.mapToStudent(dto);

        //Then
        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getFirstname(), actual.getFirstname());
        assertEquals(dto.getLastname(), actual.getLastname());
    }

}

