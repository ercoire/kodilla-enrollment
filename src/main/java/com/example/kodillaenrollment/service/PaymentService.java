package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.Course;
import com.example.kodillaenrollment.domain.Payment;
import com.example.kodillaenrollment.domain.Student;
import com.example.kodillaenrollment.repository.CourseRepository;
import com.example.kodillaenrollment.repository.PaymentRepository;
import com.example.kodillaenrollment.repository.StudentRepository;
import com.example.kodillaenrollment.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;

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
