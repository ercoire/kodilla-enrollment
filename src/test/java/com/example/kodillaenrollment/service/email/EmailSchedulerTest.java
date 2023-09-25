package com.example.kodillaenrollment.service.email;

import com.example.kodillaenrollment.domain.CourseEnrollmentNotification;
import com.example.kodillaenrollment.domain.Mail;
import com.example.kodillaenrollment.repository.EnrollmentNotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailSchedulerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private EnrollmentNotificationRepository enrollmentNotificationRepository;

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Test
    void shouldSendEmail() {
        //Given
        CourseEnrollmentNotification n = new CourseEnrollmentNotification(1L, "test@test.com",
                "test name", "title");
        when(enrollmentNotificationRepository.findOne(Example.of(new CourseEnrollmentNotification())))
                .thenReturn(Optional.of(n));

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(emailService, times(1)).send(Mockito.any(Mail.class));
        verify(enrollmentNotificationRepository, times(1)).deleteById(n.getId());

    }

    @Test
    void shouldNotSendEmailWhenNotificationNotPresent() {
        //Given
        when(enrollmentNotificationRepository.findOne(Example.of(new CourseEnrollmentNotification())))
                .thenReturn(Optional.empty());

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(emailService, times(0)).send(Mockito.any(Mail.class));
        verify(enrollmentNotificationRepository, times(0)).deleteById(anyLong());

    }

}