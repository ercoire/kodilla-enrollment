package com.example.kodillaenrollment.repository;

import com.example.kodillaenrollment.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TeacherRepository extends JpaRepository <Teacher, Long> {
}
