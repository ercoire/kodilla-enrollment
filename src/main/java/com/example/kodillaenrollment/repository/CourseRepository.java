package com.example.kodillaenrollment.repository;

import com.example.kodillaenrollment.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {
}
