package com.example.kodillaenrollment.repository;

import com.example.kodillaenrollment.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
}
