package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Event;
import com.example.kodillaenrollment.domain.EventDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventMapperTest {

    @Autowired
    EventMapper eventMapper;

    @Test
    void shouldMapToEventDto() {
//        //Given
//        Event event = new Event(1L, "test",
//                LocalDate.now(), LocalDate.of(2023, 11, 1),
//                100, new ArrayList<>());
//
//        //When
//        EventDto actual = eventMapper.mapToEventDto(event);
//
//        //Then
//        assertEquals(event.getId(), actual.getId());
//        assertEquals(event.getPrice(), actual.getPrice());
//        assertEquals(event.getEventAttendance().size(), actual.getEventAttendance().size());
//    }
    }

    @Test
    void shouldMapToEvent(){
//        //Given
//        EventDto dto = new EventDto(1L, "test",
//                LocalDate.now(), LocalDate.of(2023, 11, 1),
//                100, new ArrayList<>());
//        //When
//        Event actual = eventMapper.mapToEvent(dto);
//
//        //Then
//        assertEquals(dto.getId(), actual.getId());
//        assertEquals(dto.getStartDate(), actual.getStartDate());
//        assertEquals(dto.getEventAttendance().size(), actual.getEventAttendance().size());
    }
}