package com.calendar.components;

import com.calendar.data.EventPostRequest;
import com.calendar.models.Event;

import com.calendar.models.EventGroup;
import com.calendar.models.User;
import com.calendar.services.EventGroupService;
import com.calendar.services.EventService;
import com.calendar.services.UserService;
import com.calendar.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventRequestPostBuilder implements RequestPostBuilder<Event, EventPostRequest> {
    private DateParser dateParser;
    private UserService userService;
    private EventGroupService eventGroupService;


    @Autowired
    public EventRequestPostBuilder(DateParser dateParser, UserService userService, EventGroupService eventGroupService){
        this.dateParser = dateParser;
        this.userService = userService;
        this.eventGroupService = eventGroupService;
    }

    @Override
    public Event build(EventPostRequest request) {
        Event event = new Event();

        event.setName(request.getName());

        String comment = request.getComment();
        if(comment.length() > 0)
            event.setComment(comment);

        String playedString = request.getPlayed();
        event.setPlayed(dateParser.parseDateTime(playedString));

        Long userId = request.getUserId();
        User user = userService.findById(userId);
        if(event != null)
            event.setUser(user);

        Long groupId = request.getGroupId();
        EventGroup group = eventGroupService.findById(groupId);
        if(group != null)
            event.setGroup(group);

        String status = request.getStatus();
        if(status != null)
            event.setStatus(Event.EventStatus.valueOf(status));

        return event;
    }
}
