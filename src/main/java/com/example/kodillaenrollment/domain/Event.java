package com.example.kodillaenrollment.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "price")
    private int price;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "EVENT_STUDENT",
//           joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
//         //   inverseJoinColumns = {@JoinColumn(name = "carts_id", referencedColumnName = "id")}
//    )
  //  private List<Student> eventAttendance = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
