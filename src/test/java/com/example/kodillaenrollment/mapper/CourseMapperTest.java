package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseDto;
import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.domain.TeacherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMapperTest {

    @Autowired
    CourseMapper courseMapper;


    @Test
    void shouldMapToCourse(){
        //Given
        TeacherDto t = new TeacherDto(1L, "name1", "name2", "test");
        List<TeacherDto> test = new ArrayList<>();
        test.add(t);
        CourseDto dto = new CourseDto(1L, "title", test,
                LocalDate.of(2023,1,1),
                LocalDate.of(2023,12,31),
                100, "test", 70,"Mon",
                LocalTime.now());

        //When
        Course actual = courseMapper.mapToCourse(dto);

        //Then
        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getStartingDate(), actual.getStartingDate());
        assertEquals(dto.getAssignedTeachers().get(0).getId(), actual.getAssignedTeachers().get(0).getId());

    }

    @Test
    void shouldMapToCourseDto(){
        //Given
        Teacher t = new Teacher(1L, "name1", "name2", null, "test");
        List<Teacher> test = new ArrayList<>();
        test.add(t);
        Course course = new Course(1L, "title", test, List.of(),
                LocalDate.of(2023,1,1),
                LocalDate.of(2023,12,31),
                100, "test", 70,"Mon",
                LocalTime.now(), List.of());

        //When
        CourseDto actual = courseMapper.mapToCourseDto(course);

        //Then
        assertEquals(course.getId(), actual.getId());
        assertEquals(course.getStartingDate(), actual.getStartingDate());
        assertEquals(course.getAssignedTeachers().get(0).getId(), actual.getAssignedTeachers().get(0).getId());

    }

    @Test
    void shouldMapToCourseDtoList(){
        //Given
        List<Course> list = new ArrayList<>();
        Course dto1 = new Course(1L, "title1", new ArrayList<>(), new ArrayList<>(),
                LocalDate.of(2023,01,01), LocalDate.of(2023,12,31),
                10, "dummy", 50, "Sunday",
                LocalTime.of(19,00),  new ArrayList<>());
        Course dto2 = new Course(2L, "title2", new ArrayList<>(), new ArrayList<>(),
                LocalDate.of(2023,6,7), LocalDate.of(2023,11,10),
                50, "dummy2", 40, "Monday",
                LocalTime.of(19,00),  new ArrayList<>());
        list.add(dto1);
        list.add(dto2);

        //When
        List<CourseDto> actual = courseMapper.mapToCourseDtoList(list);

        //Then
        assertEquals(2, actual.size());
        assertEquals(list.get(0).getId(), actual.get(0).getId());
        assertEquals(list.get(1).getId(), actual.get(1).getId());
        assertEquals(list.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(list.get(1).getTitle(), actual.get(1).getTitle());
    }

}