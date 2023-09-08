package com.example.kodillaenrollment.service;
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
    private final EventRepository eventRepository;
    private final PaymentRepository paymentRepository;
}
