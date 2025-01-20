package com.example.bupdashboard.controller;

import com.example.bupdashboard.eventsDb.entity.Event;
import com.example.bupdashboard.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents(){
        List<Event> eventList = this.eventService.fetchAllEvents();
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }
}
