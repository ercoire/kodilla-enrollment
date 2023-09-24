package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.repository.CourseRepository;
import com.example.kodillaenrollment.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseService {


    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public void saveCourse(final Course course) {
        courseRepository.save(course);
    }

    public Course updateCourse(final Course course) {
        Course toBeUpdated = courseRepository.findById(course.getId()).get();
        course.setPayment(toBeUpdated.getPayment());
        course.setStudents(toBeUpdated.getStudents());
        return courseRepository.save(course);
    }

    public Course getCourse(long id) throws NoSuchElementException {
        return courseRepository.findById(id).get();
    }

    public List<Course> getAllCourses() throws NoSuchElementException {
        return courseRepository.findAll();
    }

    public void deleteCourse(long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public void addStudentToCourse(Long courseId, Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        List<Course> studentCourses = student.getCourseList();
        studentCourses.add(course);
        List<Student> courseStudents = course.getStudents();
        courseStudents.add(student);
        studentRepository.save(student);
        courseRepository.save(course);
        //todo save notific
    }
}
