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
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PaymentRepository paymentRepository;

    //course
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

    public void addStudentToCourse(Long courseId, Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        List<Course> studentCourses = student.getCourseList();
        studentCourses.add(course);
        List<Student> courseStudents = course.getStudents();
        courseStudents.add(student);
        studentRepository.save(student);
        courseRepository.save(course);
    }

    //teacher
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

    //student
    public void saveStudent(final Student student) {
        studentRepository.save(student);
    }

    public Student updateStudent(final Student student){
        Student toBeUpdated = studentRepository.findById(student.getId()).get();
        student.setCourseList(toBeUpdated.getCourseList());
        student.setPayments(toBeUpdated.getPayments());
        return studentRepository.save(student);
    }

    public Student getStudent(long id) throws NoSuchElementException {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudents() throws NoSuchElementException {
        return studentRepository.findAll();
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public List<Course> getCoursesByStudentId(long id){
        Student student = getStudent(id);
        return student.getCourseList();
    }

    public List<Payment> getPaymentsByStudentId(long id){
        Student student = getStudent(id);
        return student.getPayments();
    }


    //payment
    public List<Payment> getAllPayments() throws NoSuchElementException {
        return paymentRepository.findAll();
    }

    public void savePayment(final Payment payment, Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(payment.getCourseId()).get();
        List<Payment> studentPayments = student.getPayments();
        studentPayments.add(payment);
        List<Payment> coursePayments = course.getPayment();
        coursePayments.add(payment);
        payment.setStudent(student);
        paymentRepository.save(payment);
        studentRepository.save(student);
        courseRepository.save(course);
    }


}
