package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    public Payment(Long id, LocalDate paymentDate, int amount, Long courseId) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.courseId = courseId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
