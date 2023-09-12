package com.example.kodillaenrollment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class EventDto {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
}
