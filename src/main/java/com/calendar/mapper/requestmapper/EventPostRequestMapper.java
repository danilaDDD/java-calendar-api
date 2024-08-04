package com.calendar.mapper.requestmapper;

import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.requests.EventRequestBody;
import org.springframework.stereotype.Component;

@Component
public class EventPostRequestMapper implements RequestEntityWithUserMapper<Event, EventRequestBody> {

    @Override
    public Event entityFromRequestBody(EventRequestBody requestBody, User user) {

        Event event = new Event();

        event.setName(requestBody.getName());

        String comment = requestBody.getComment();
        if (comment != null)
            event.setComment(comment);


        event.setPlayed(requestBody.getPlayed());
        event.setUser(user);

        Event.EventStatus status = requestBody.getStatus();
        if (status != null)
            event.setStatus(status);

        return event;
    }
}
