package com.example.kodillaenrollment.controller;

import com.example.kodillaenrollment.domain.Event;
import com.example.kodillaenrollment.domain.EventDto;
import com.example.kodillaenrollment.mapper.EventMapper;
import com.example.kodillaenrollment.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final DbService dbService;
    private final EventMapper eventMapper;

    @GetMapping(value = "{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long eventId) {
        Event event = dbService.getEvent(eventId);
        return ResponseEntity.ok(eventMapper.mapToEventDto(event));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEvent(@RequestBody EventDto eventDto){
        Event event = eventMapper.mapToEvent(eventDto);
        dbService.saveEvent(event);
        return ResponseEntity.ok().build();
    }
}
