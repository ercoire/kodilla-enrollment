package com.example.kodillaenrollment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EVENTS")
public class Event {

    @Id
    @Column(name = "id")
    private Long id;

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
