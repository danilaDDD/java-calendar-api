package com.calendar.mapper.responsemapper;

import com.calendar.components.DateFormatter;
import com.calendar.responses.EventResponse;
import com.calendar.responses.EventsResponse;
import com.calendar.models.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventResponseMapper extends AbstractEntityResponseMapper<Event, EventResponse, EventsResponse> {
    DateFormatter dateFormatter;

    @Override
    public EventResponse entityToResponse(Event event) {
        return new EventResponse()
                .setId(event.getId())
                .setName(event.getName())
                .setComment(event.getComment())
                .setStatus(event.getStatus())
                .setPlayed(dateFormatter.printDateTime(event.getPlayed()))
                .setUser(event.getUser().getLogin());
    }
}
