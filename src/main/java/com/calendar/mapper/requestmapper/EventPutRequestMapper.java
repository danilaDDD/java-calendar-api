package com.calendar.mapper.requestmapper;

import com.calendar.components.DateFormatter;
import com.calendar.exceptions.BadRequestException;
import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.requests.EventRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
@AllArgsConstructor
public class EventPutRequestMapper implements PutRequestEntityMapper<Event, EventRequestBody> {
    DateFormatter dateFormatter;


    @Override
    public Event update(Event event, EventRequestBody requestBody) {
        try {
            String name = requestBody.getName();
            if (name != null)
                event.setName(name);

            String comment = requestBody.getComment();
            if (comment != null)
                event.setComment(comment);

            String status = String.valueOf(requestBody.getStatus());
            if (status != null)
                event.setStatus(Event.EventStatus.valueOf(status));

            String playedString = requestBody.getPlayed();
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
