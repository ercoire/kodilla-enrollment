package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EVENTS")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "price")
    private int price;

    @ManyToMany(cascade = CascadeType.ALL) //todo if all?
    @JoinTable(
            name = "EVENT_STUDENT",
            joinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")}
    )
    private List<Student> eventAttendance = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<Payment> payments;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
