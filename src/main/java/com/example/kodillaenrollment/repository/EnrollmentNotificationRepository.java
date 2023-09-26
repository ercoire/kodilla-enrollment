package com.example.kodillaenrollment.repository;


import com.example.kodillaenrollment.domain.CourseEnrollmentNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EnrollmentNotificationRepository extends JpaRepository<CourseEnrollmentNotification, Long> {
}