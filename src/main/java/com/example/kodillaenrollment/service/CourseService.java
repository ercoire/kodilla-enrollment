package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.CourseEnrollmentNotification;
import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.domain.Teacher;
import com.example.kodillaenrollment.repository.CourseRepository;
import com.example.kodillaenrollment.repository.EnrollmentNotificationRepository;
import com.example.kodillaenrollment.repository.StudentRepository;
import com.example.kodillaenrollment.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {


    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final EnrollmentNotificationRepository enrollmentNotificationRepository;

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
        Optional<Course> selected = courseRepository.findById(id);
        Course course = selected.get();
        List<Teacher> teachers = course.getAssignedTeachers();
        teachers.forEach(teacher -> {
            teacher.getAssignedCourses().remove(course);
            teacherRepository.save(teacher);
        });
        List<Student> students = course.getStudents();
        students.forEach(student -> {
            student.getCourseList().remove(course);
            studentRepository.save(student);
        });
        course.setAssignedTeachers(new ArrayList<>());
        course.setStudents(new ArrayList<>());
        courseRepository.save(course);
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
        CourseEnrollmentNotification notification = new CourseEnrollmentNotification(
                student.getEmail(),
                student.getFirstname() + " " + student.getLastname(),
                course.getTitle());
        enrollmentNotificationRepository.save(notification);
    }

}
