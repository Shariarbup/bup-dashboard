package com.example.bupdashboard.service;

import com.example.bupdashboard.eventsDb.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> fetchAllEvents();
}
