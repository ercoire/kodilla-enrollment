package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.*;
import com.example.kodillaenrollment.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DbService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository; //todo
    private final TeacherRepository teacherRepository;
    private final EventRepository eventRepository;
    private final PaymentRepository paymentRepository;

    //course
    public Course saveCourse(final Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(final Course course){
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

    //teacher
    public Teacher saveTeacher(final Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacher(long id) throws NoSuchElementException {
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    public void deleteTeacher(long id) {
        teacherRepository.deleteById(id);
    }

    //student
    public Student saveStudent(final Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(long id) throws NoSuchElementException {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudents() throws NoSuchElementException {
        return studentRepository.findAll();
    }

    public void deleteStudent(long id) {
        courseRepository.deleteById(id);
    }

    //event
    public Event getEvent(long id) throws NoSuchElementException {
        return eventRepository.findById(id).get();
    }

    public void saveEvent(final Event event) {
        eventRepository.save(event);
    }

    //payment
    public List<Payment> getAllPayments()throws NoSuchElementException{
        return paymentRepository.findAll();
    }

    public void savePayment(final Payment payment){
        paymentRepository.save(payment);
    }

}
