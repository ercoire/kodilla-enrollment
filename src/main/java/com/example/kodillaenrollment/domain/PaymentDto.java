package com.example.kodillaenrollment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class PaymentDto {

    private Long id;
    private Date paymentDate;
    private String student;
    private int amount;
    private int courseId;
    private int eventId;

}
