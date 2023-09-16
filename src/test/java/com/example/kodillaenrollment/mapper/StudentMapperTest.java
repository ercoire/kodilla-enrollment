package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.StudentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    void shouldMapToStudentDto() {
        //Given
        Student test = new Student(1L, "firstname", "lastname");

        //When
        StudentDto actual = studentMapper.mapToStudentDto(test);

        //Then
        assertEquals(test.getId(), actual.getId());
        assertEquals(test.getFirstname(), actual.getFirstname());
        assertEquals(test.getLastname(), actual.getLastname());
    }

    @Test
    void shouldMapToStudent() {
        //Given
        StudentDto test = new StudentDto(1L, "firstname", "lastname");

        //When
        Student actual = studentMapper.mapToStudent(test);

        //Then
        assertEquals(test.getId(), actual.getId());
        assertEquals(test.getFirstname(), actual.getFirstname());
        assertEquals(test.getLastname(), actual.getLastname());
    }

    @Test
    void shouldMapToStudentDtoList(){
       //Given
        Student student = new Student(null, "first", "last");
        List<Student> list = new ArrayList<>();
        list.add(student);

        //When
        List<StudentDto> actual = studentMapper.mapToStudentDtoList(list);

        //Then
        assertEquals(1, actual.size());
        assertEquals(list.get(0).getId(), actual.get(0).getId());
        assertEquals(list.get(0).getFirstname(), actual.get(0).getFirstname());
        assertEquals(list.get(0).getLastname(), actual.get(0).getLastname());

    }
}

