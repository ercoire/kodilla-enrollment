package com.example.kodillaenrollment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "PAYMENTS")
public class Payment {

    @Id
    @Column(name = "id")
    private Long id;

    private Date paymentDate;
    private String student;
    private int amount;
    private int courseId;
    private int eventId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
