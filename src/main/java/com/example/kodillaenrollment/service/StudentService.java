package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    //student
    public void saveStudent(final Student student) {
        studentRepository.save(student);
    }

    public Student updateStudent(final Student student) {
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

    public List<Course> getCoursesByStudentId(long id) {
        Student student = getStudent(id);
        return student.getCourseList();
    }

    public List<Payment> getPaymentsByStudentId(long id) {
        Student student = getStudent(id);
        return student.getPayments();
    }


}
