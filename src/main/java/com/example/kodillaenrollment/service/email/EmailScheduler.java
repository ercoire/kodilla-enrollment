package com.example.kodillaenrollment.service.email;

import com.example.kodillaenrollment.domain.CourseEnrollmentNotification;
import com.example.kodillaenrollment.domain.Mail;
import com.example.kodillaenrollment.repository.EnrollmentNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final EmailService emailService;
    private final EnrollmentNotificationRepository enrollmentNotificationRepository;

    private static final String SUBJECT = "Enrollment Notification";


    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void sendInformationEmail() {
        Optional<CourseEnrollmentNotification> notification =
                enrollmentNotificationRepository.findOne(Example.of(new CourseEnrollmentNotification()));
        String message;
        if (notification.isPresent()) {
            CourseEnrollmentNotification n = notification.get();
            message = "Dear " + n.getStudentName() + ", Welcome to " + n.getCourseTitle() +
                    "! Thank you for your registration. Further details will some in a separate communication. " +
                    "With best regards, Happy Steps";

            emailService.send(new Mail(
                    // n.getEmail(), // in real production environment
                    "admin@email.com", // for test purposes
                    SUBJECT,
                    message
            ));
            enrollmentNotificationRepository.deleteById(n.getId());
        }
    }
}
