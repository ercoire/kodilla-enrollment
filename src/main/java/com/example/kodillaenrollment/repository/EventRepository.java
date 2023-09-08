package com.example.kodillaenrollment.repository;

import com.example.kodillaenrollment.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
}
