package com.calendar.requests;

import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.services.EventGroupService;
import com.calendar.services.UserService;
import com.calendar.interfacies.DateFormatter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// FIXME repaint to validator

@Component
@Setter
@Getter
public class EventPostRequest implements AuthByUserPostRequest<Event> {
    @Setter private EventGroupService eventGroupService;
    @Setter private UserService userService;

    private DateFormatter dateParser;

    @Autowired
    public void setDateParser(DateFormatter dateParser){
        this.dateParser = dateParser;
    }

    String name;
    String comment = "";
    String played;
    String status = Event.EventStatus.ENABLE.name();

    @Override
    public Event create(User user){
        Event event = new Event();
        event.setName(name);

        if(!comment.isEmpty())
            event.setComment(comment);

        LocalDateTime played = dateParser.parseDateTime(this.getPlayed());
        if(played.compareTo(LocalDateTime.now()) > -1)
            event.setPlayed(played);

        event.setUser(user);

        if(!status.isEmpty()) {
            Event.EventStatus eventStatus = Event.EventStatus.valueOf(status);
            event.setStatus(eventStatus);
        }

        return event;
    }
}
