package com.VehiclesCommunity.Vehicles.Community.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/Event")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
    }
    @GetMapping("/Event")
    public ResponseEntity<Map<String,List<Event>>> getEvents(){
        return ResponseEntity.ok(Map.of("events", eventService.getEvents()));
    }
    @GetMapping("/Eventid")
    public ResponseEntity<Map<String,Optional<Event>>>getEvent(@PathVariable Integer id){
        return ResponseEntity.ok(Map.of("events", eventService.getEvent(id)));
    }
    @PostMapping("/addEvent")
    public ResponseEntity<Map<String,String>>addEvent(@RequestBody Event event){
        eventService.addEvent(event);
        return ResponseEntity.ok(Map.of("massage","Event added"));
    }
    @DeleteMapping("/Eventid")
    public ResponseEntity<Map<String,String>>deleteEvent(@PathVariable Integer id){
        Map<String,String> response = new HashMap<>();
        boolean exists = eventService.deleteEvent(id);
        if(!exists){
            response.put("message","Event not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("message","Event deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}