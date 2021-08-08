package com.calendar.controllers;

import com.calendar.models.EventGroup;
import com.calendar.services.EventGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-groups/")
@Transactional
public class EventGroupController {
    @Autowired
    private EventGroupService eventNameService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventGroup> getAll(){
        return (List<EventGroup>) this.eventNameService.findAll();

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postEventGroup(@RequestBody EventGroup eventGroup){
        eventNameService.save(eventGroup);
        return ResponseEntity.ok().build();
    }
}
