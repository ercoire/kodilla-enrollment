package com.example.kodillaenrollment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PaymentDto {

    private Long id;
    private LocalDate paymentDate;
    private Student student;
    private int amount;
    private Long courseId;
    private Long eventId;

}
