package com.calendar.controllers;

import com.calendar.components.EventRequestPostBuilder;
import com.calendar.components.EventRequestPutBuilder;
import com.calendar.data.EventResponce;
import com.calendar.models.Event;
import com.calendar.data.EventPutRequest;
import com.calendar.services.EventGroupService;
import com.calendar.services.EventService;
import com.calendar.services.UserService;
import com.calendar.data.EventPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events/")
public class EventController {
    private EventService service;
    private EventRequestPostBuilder eventRequestPostBuilder;
    private EventRequestPutBuilder eventRequestPutBuilder;

    @Autowired
    public void setService(EventService service){
        this.service = service;
    }

    @Autowired
    public void setEventRequestPostBuilder(EventRequestPostBuilder postBuilder){
        this.eventRequestPostBuilder = postBuilder;
    }

    @Autowired
    public void setEventRequestPutBuilder(EventRequestPutBuilder eventRequestPutBuilder){
        this.eventRequestPutBuilder = eventRequestPutBuilder;
    }


    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT)
    public ResponseEntity<Void> putEvent(
            @PathVariable(name="id", required = true) Long id,
            @RequestBody EventPutRequest requestBody
            )
    {
        try {
            Event event = eventRequestPutBuilder.build(id, requestBody);
            if(event != null) {
                service.update(id, event);
                return ResponseEntity.status(200).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(400).build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postEvent(@RequestBody EventPostRequest requestBody){
        try{
            Event event = eventRequestPostBuilder.build(requestBody);
            if(event != null) {
                Event savedEvent = service.save(event);
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
