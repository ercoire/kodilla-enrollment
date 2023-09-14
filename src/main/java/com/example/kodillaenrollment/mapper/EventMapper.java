package com.example.kodillaenrollment.mapper;

import com.example.kodillaenrollment.domain.Event;
import com.example.kodillaenrollment.domain.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventMapper {

    public EventDto mapToEventDto(final Event event) {
        return new EventDto(
                event.getId(),
                event.getTitle(),
                event.getStartDate(),
                event.getEndDate(),
                event.getPrice()
        );
    }

    public Event mapToEvent(final EventDto eventDto) {
        return new Event(
                eventDto.getId(),
                eventDto.getTitle(),
                eventDto.getStartDate(),
                eventDto.getEndDate(),
                eventDto.getPrice(),
                List.of(),
                List.of()
        );
    }
}
