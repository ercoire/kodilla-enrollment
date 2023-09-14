package com.example.kodillaenrollment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventDto {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;

}
