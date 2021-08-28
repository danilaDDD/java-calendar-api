package com.calendar.controllers;

import com.calendar.components.EventRequestPostBuilder;
import com.calendar.components.EventRequestPutBuilder;
import com.calendar.data.EventResponse;
import com.calendar.models.Event;
import com.calendar.data.EventPutRequest;
import com.calendar.models.User;
import com.calendar.services.EventService;
import com.calendar.data.EventPostRequest;

import com.calendar.services.UserService;
import com.calendar.interfacies.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/events/")
public class EventController {
    private EventService eventService;
    private UserService userService;

    private EventRequestPostBuilder eventRequestPostBuilder;
    private EventRequestPutBuilder eventRequestPutBuilder;

    private DateFormatter dateParser;

    @Autowired
    public void setDateParser(DateFormatter dateParser){
        this.dateParser = dateParser;
    }

    @Autowired
    public void setEventService(EventService eventService){
        this.eventService = eventService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setEventRequestPostBuilder(EventRequestPostBuilder postBuilder){
        this.eventRequestPostBuilder = postBuilder;
    }

    @Autowired
    public void setEventRequestPutBuilder(EventRequestPutBuilder eventRequestPutBuilder){
        this.eventRequestPutBuilder = eventRequestPutBuilder;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventResponse> findAll(
            @RequestParam(name = "userId", required = true) Long userId,
            @RequestParam(name = "status", required = false) String status, 
            @RequestParam(name = "from", required = false) String fromDateString,
            @RequestParam(name = "to", required = false) String toDateString
    )
    {
        User user = userService.findById(userId);
        List<Event> events = eventService.findByUser(user);

        boolean buildFromStream = false;
        Stream<Event> eventsStream = events.stream();

        if(status != null){
            Event.EventStatus eventStatus = Event.EventStatus.valueOf(status);
            eventsStream = eventsStream.filter(event -> event.getStatus().equals(eventStatus));
        }

        if(fromDateString != null){
            LocalDateTime fromDate = dateParser.parseDateTime(fromDateString);
            eventsStream = eventsStream.filter(event -> event.getPlayed().compareTo(fromDate) > 0);
        }

        if(toDateString != null){
            LocalDateTime toDate = dateParser.parseDateTime(toDateString);
            eventsStream = eventsStream.filter(event -> event.getPlayed().compareTo(toDate) < 0);
        }


        events = eventsStream
                .sorted(new Comparator<Event>() {
                    @Override
                    public int compare(Event e1, Event e2) {
                        return e1.getPlayed().compareTo(e2.getPlayed());
                    }
                })

                .collect(Collectors.toList());

        return serialize(events);
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
                eventService.update(id, event);
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
                Event savedEvent = eventService.save(event);
                if(savedEvent != null)
                    return ResponseEntity.status(201).build();
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(400).build();
    }

    public List<EventResponse> serialize(List<Event> events){
        return events.stream().map(EventResponse::new).collect(Collectors.toList());
    }

    public EventResponse serialize(Event event){
        if(event != null)
            return new EventResponse(event);
        else
            return new EventResponse();
    }


}
