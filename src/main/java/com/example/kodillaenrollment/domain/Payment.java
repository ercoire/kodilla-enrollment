package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "amount")
    private int amount;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "event_id")
    private Long eventId;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
