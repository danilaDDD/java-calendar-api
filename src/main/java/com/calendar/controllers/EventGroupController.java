package com.calendar.controllers;

import com.calendar.data.EventGroupResponce;
import com.calendar.models.EventGroup;
import com.calendar.services.EventGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event-groups/")
@Transactional
public class EventGroupController {
    @Autowired
    private EventGroupService eventGroupService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventGroupResponce> getAll(){
        return serialize(this.eventGroupService.findAll());

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postEventGroup(@RequestBody EventGroup eventGroup){
        eventGroupService.save(eventGroup);
        return ResponseEntity.status(201).build();
    }

    public List<EventGroupResponce> serialize(List<EventGroup> groups){
        return groups.stream().map(EventGroupResponce::new).collect(Collectors.toList());
    }
}
