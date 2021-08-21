package com.calendar.data;

import com.calendar.models.Event;
import com.calendar.models.EventGroup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
public class EventResponce implements Serializable {
    String name = "";
    String comment = "";
    Event.EventStatus status = Event.EventStatus.ENABLE;
    LocalDateTime played;
    EventGroup group;

    public EventResponce(){

    }

    public EventResponce(Event event){
        this.setName(event.getName())
                .setComment(event.getComment())
                .setStatus(event.getStatus())
                .setPlayed(event.getPlayed())
                .setGroup(event.getGroup());
    }
}
