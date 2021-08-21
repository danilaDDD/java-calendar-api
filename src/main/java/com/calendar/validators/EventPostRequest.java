package com.calendar.validators;

import com.calendar.models.Event;
import com.calendar.models.EventGroup;
import com.calendar.models.User;
import com.calendar.services.EventGroupService;
import com.calendar.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// FIXME repaint to validator

@Setter
@Getter
public class EventPostRequest {
    @Setter private EventGroupService eventGroupService;
    @Setter private UserService userService;

    String name;
    String comment = "";
    String played;

    Integer userId;
    Long groupId;
    String status = "";


    public Event create(){
        Event event = new Event();
        event.setName(name);

        if(comment.length() > 0)
            event.setComment(comment);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime playedDateTime = LocalDateTime.parse(played, formatter);
        event.setPlayed(playedDateTime);

        EventGroup group = eventGroupService.findById(groupId);
        event.setGroup(group);

        User user = userService.findById(userId);
        event.setUser(user);

        if(status.length() > 0) {
            Event.EventStatus eventStatus = Event.EventStatus.valueOf(status);
            event.setStatus(eventStatus);
        }

        return event;
    }
}
