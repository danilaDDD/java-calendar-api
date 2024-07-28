package com.calendar.mapper.requestmapper;

import com.calendar.components.DateFormatter;
import com.calendar.exceptions.BadRequestException;
import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.requests.EventRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeParseException;

@Component
@AllArgsConstructor
public class EventPostRequestMapper implements RequestEntityWithUserMapper<Event, EventRequestBody> {
    DateFormatter dateFormatter;

    @Override
    public Event entityFromRequestBody(EventRequestBody request, User user) {
        try {
            Event event = new Event();

            event.setName(request.getName());

            String comment = request.getComment();
            if (comment != null)
                event.setComment(comment);

            String playedString = request.getPlayed();
            event.setPlayed(dateFormatter.parseDateTime(playedString));
            event.setUser(user);

            Event.EventStatus status = request.getStatus();
            if (status != null)
                event.setStatus(status);

            return event;
        }catch (DateTimeParseException e){
            throw new BadRequestException("parsed event played date format");
        }
    }
}
