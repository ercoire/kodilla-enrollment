package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;


    public void saveTeacher(final Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Teacher getTeacher(long id) throws NoSuchElementException {
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    public Teacher updateTeacher(final Teacher teacher) {
        Teacher toBeUpdated = teacherRepository.findById(teacher.getId()).get();
        teacher.setAssignedCourses(toBeUpdated.getAssignedCourses());
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(long id) {
        teacherRepository.deleteById(id);
    }

    public List<Course> getCoursesByTeacher(long id) throws NoSuchElementException {
        Teacher teacher = getTeacher(id);
        return teacher.getAssignedCourses();
    }
}
