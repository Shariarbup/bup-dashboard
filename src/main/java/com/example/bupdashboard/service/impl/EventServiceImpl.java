package com.example.bupdashboard.service.impl;

import com.example.bupdashboard.eventsDb.entity.Event;
import com.example.bupdashboard.eventsDb.repository.EventRepository;
import com.example.bupdashboard.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;


    @Override
    public List<Event> fetchAllEvents() {
        return eventRepository.findAll();
    }
}
