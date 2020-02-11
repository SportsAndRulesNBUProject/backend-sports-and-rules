package com.nbu.sportsandrules.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbu.sportsandrules.entity.Event;
import com.nbu.sportsandrules.repository.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event getEventById(Integer id) {
        return eventRepository.findById(id).get();
    }

    public List<Event> getHostTeamEvents(Integer id) {
        return eventRepository.findByHostTeamId(id);
    }

    public List<Event> getGuestTeamEvents(Integer id) {
        return eventRepository.findByGuestTeamId(id);
    }

    public List<Event> getEventsByDate(OffsetDateTime date) {
        return eventRepository.findByDate(date);
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(e -> events.add(e));
        return events;
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

}
