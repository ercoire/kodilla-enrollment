package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.domain.TeacherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TeacherMapperTest {

    @Autowired
    TeacherMapper teacherMapper;

    @Test
    void shouldMapToTeacherDto() {
        //Given
        Teacher test = new Teacher(1L, "first", "last", "dummy");

        //When
        TeacherDto actual = teacherMapper.mapToTeacherDto(test);

        //Then
        assertEquals(test.getId(), actual.getId());
        assertEquals(test.getFirstname(), actual.getFirstname());
        assertEquals(test.getLastname(), actual.getLastname());
        assertEquals(test.getDescription(), actual.getDescription());
    }

    @Test
    void shouldMapToTeacher() {
        //Given
        TeacherDto dto = new TeacherDto(1L, "first", "last", "dummy");

        //When
        Teacher actual = teacherMapper.mapToTeacher(dto);

        //Then
        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getFirstname(), actual.getFirstname());
        assertEquals(dto.getLastname(), actual.getLastname());
        assertEquals(dto.getDescription(), actual.getDescription());
        assertEquals(0, actual.getAssignedCourses().size());
    }

    @Test
    void shouldMapToTeacherDtoList() {
        //Given
        Teacher t = new Teacher(1L, "first", "last", "dummy");
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(t);

        //When
        List<TeacherDto> actual = teacherMapper.mapToTeacherDtoList(teacherList);

        //Then
        assertEquals(teacherList.size(), actual.size());
        assertEquals(teacherList.get(0).getId(), actual.get(0).getId());
        assertEquals(teacherList.get(0).getFirstname(), actual.get(0).getFirstname());
        assertEquals(teacherList.get(0).getLastname(), actual.get(0).getLastname());
        assertEquals(teacherList.get(0).getDescription(), actual.get(0).getDescription());
    }

    @Test
    void shouldMapToTeacherList() {
        //Given
        TeacherDto dto = new TeacherDto(1L, "first", "last", "dummy");
        List<TeacherDto> teacherDtoList = new ArrayList<>();
        teacherDtoList.add(dto);

        //When
        List<Teacher> actual = teacherMapper.mapToTeacherList(teacherDtoList);

        //Then
        assertEquals(teacherDtoList.size(), actual.size());
        assertEquals(teacherDtoList.get(0).getId(), actual.get(0).getId());
        assertEquals(teacherDtoList.get(0).getFirstname(), actual.get(0).getFirstname());
        assertEquals(teacherDtoList.get(0).getLastname(), actual.get(0).getLastname());
        assertEquals(teacherDtoList.get(0).getDescription(), actual.get(0).getDescription());
    }

}