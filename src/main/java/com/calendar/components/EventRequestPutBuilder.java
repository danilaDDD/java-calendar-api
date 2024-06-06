package com.calendar.components;

import com.calendar.requests.EventPutRequest;
import com.calendar.interfacies.RequestPutBuilder;
import com.calendar.models.Event;
import com.calendar.services.EventService;
import com.calendar.interfacies.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventRequestPutBuilder implements RequestPutBuilder<Event, EventPutRequest> {
    private final DateFormatter dateParser;
    private final EventService eventService;

    @Autowired
    public EventRequestPutBuilder(DateFormatter dateParser, EventService eventService){
        this.eventService = eventService;
        this.dateParser = dateParser;
    }

    @Override
    public Event build(Long id, EventPutRequest eventPutRequest) {
        Event event = eventService.findById(id);

        String name = eventPutRequest.getName();
        if(name != null)
            event.setName(name);

        String comment = eventPutRequest.getComment();
        if(comment != null)
            event.setComment(comment);

        String status = eventPutRequest.getStatus();
        if(status != null)
            event.setStatus(Event.EventStatus.valueOf(status));

        String playedString = eventPutRequest.getPlayed();
        if(playedString != null) {
            LocalDateTime played = dateParser.parseDateTime(playedString);
            if(played.compareTo(LocalDateTime.now()) > -1)
                event.setPlayed(played);
        }

        return event;
    }
}
