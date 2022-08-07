package com.calendar.data;

import com.calendar.interfacies.DateFormatter;
import com.calendar.models.Event;
import com.calendar.models.EventGroup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
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
