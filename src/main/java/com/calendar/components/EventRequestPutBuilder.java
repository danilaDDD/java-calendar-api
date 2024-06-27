package com.calendar.components;

import com.calendar.exceptions.BadRequestException;
import com.calendar.requests.EventPutRequest;
import com.calendar.models.Event;
import com.calendar.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class EventRequestPutBuilder implements RequestPutBuilder<Event, EventPutRequest> {
    private final DateFormatter dateFormatter;
    private final EventService eventService;

    @Autowired
    public EventRequestPutBuilder(DateFormatter dateFormatter, EventService eventService){
        this.eventService = eventService;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public Event build(Long id, EventPutRequest eventPutRequest) {
        try {
            Event event = eventService.findById(id);

            String name = eventPutRequest.getName();
            if (name != null)
                event.setName(name);

            String comment = eventPutRequest.getComment();
            if (comment != null)
                event.setComment(comment);

            String status = eventPutRequest.getStatus();
            if (status != null)
                event.setStatus(Event.EventStatus.valueOf(status));

            String playedString = eventPutRequest.getPlayed();
            if (playedString != null) {
                LocalDateTime played = dateFormatter.parseDateTime(playedString);
                if (played.compareTo(LocalDateTime.now()) > -1)
                    event.setPlayed(played);
            }

            return event;
        } catch (DateTimeParseException e) {
            throw new BadRequestException("parsed event played date format");
        }
    }
}
