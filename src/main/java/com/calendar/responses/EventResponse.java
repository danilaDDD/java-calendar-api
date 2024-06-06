package com.calendar.responses;

import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class EventResponse implements Serializable {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    Integer id;
    String name;
    String comment;
    Event.EventStatus status;
    String played;
    String user;

    public EventResponse(){}

    public EventResponse(Event event){
        if(event.getId() != null)
            setId(Math.toIntExact(event.getId()));

        setName(event.getName());
        setComment(event.getComment());
        setStatus(event.getStatus());
        setPlayed(event.getPlayed().format(formatter));

        setUser(event.getUser().toString());
    }
}
