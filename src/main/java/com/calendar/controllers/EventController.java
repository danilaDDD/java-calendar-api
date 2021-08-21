package com.calendar.controllers;

import com.calendar.data.EventResponce;
import com.calendar.models.Event;
import com.calendar.services.EventGroupService;
import com.calendar.services.EventService;
import com.calendar.services.UserService;
import com.calendar.validators.EventPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events/")
public class EventController {
    private EventService eventService;
    private UserService userService;
    private EventGroupService eventGroupService;

    @Autowired
    public void setEventGroupService(EventGroupService service){
        this.eventGroupService = service;
    }

    @Autowired
    public void setUserService(UserService service){
        this.userService = service;
    }

    @Autowired
    public void setEventService(EventService service){
        this.eventService = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postEvent(@RequestBody EventPostRequest requestBody){
        try{
            requestBody.setEventGroupService(eventGroupService);
            requestBody.setUserService(userService);

            Event event = requestBody.create();
            if(event != null) {
                Event savedEvent = eventService.save(event);
                if(savedEvent != null)
                    return ResponseEntity.status(200).build();
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(400).build();
    }

    public List<EventResponce> serialize(List<Event> events){
        return events.stream().map(EventResponce::new).collect(Collectors.toList());
    }

    public EventResponce serialize(Event event){
        if(event != null)
            return new EventResponce(event);
        else
            return new EventResponce();
    }
}
