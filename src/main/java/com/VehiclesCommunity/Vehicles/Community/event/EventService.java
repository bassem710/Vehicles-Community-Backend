package com.VehiclesCommunity.Vehicles.Community.event;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public List<Event> getEvents() {
        return new ArrayList<>(eventRepository.findAll());
    }
    public Optional<Event> getEvent(Integer id) {
        return eventRepository.findById(id);
    }
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }
    public boolean deleteEvent(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return false;
        }
        eventRepository.deleteById(id);
        return true;
    }
}
