package com.calendar.responses;

import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

//@FIXME убрать поле formatter
@Getter
@Setter
public class EventResponse implements Serializable {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public EventResponse(Event event) {
        id = event.getId();
        name = event.getName();
        comment = event.getComment();
        status = event.getStatus();
        played = formatter.format(event.getPlayed());
        user = event.getUser().getLogin();
    }

    long id;
    String name;
    String comment;
    Event.EventStatus status;
    String played;
    String user;
}
