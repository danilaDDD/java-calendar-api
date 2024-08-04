package com.calendar.mapper.requestmapper;

import com.calendar.models.Event;
import com.calendar.requests.EventRequestBody;
import org.springframework.stereotype.Component;


@Component
public class EventPutRequestMapper implements PutRequestEntityMapper<Event, EventRequestBody> {

    @Override
    public Event update(Event event, EventRequestBody requestBody) {

        String name = requestBody.getName();
        if (name != null)
            event.setName(name);

        String comment = requestBody.getComment();
        if (comment != null)
            event.setComment(comment);

        if (requestBody.getStatus() != null)
            event.setStatus(requestBody.getStatus());

        if(requestBody.getPlayed() != null)
            event.setPlayed(requestBody.getPlayed());

        return event;
    }
}
