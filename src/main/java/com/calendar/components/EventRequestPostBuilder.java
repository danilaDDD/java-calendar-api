package com.calendar.components;

import com.calendar.data.EventPostRequest;
import com.calendar.interfacies.RequestPostBuilder;
import com.calendar.models.Event;

import com.calendar.models.EventGroup;
import com.calendar.models.User;
import com.calendar.services.EventGroupService;
import com.calendar.services.UserService;
import com.calendar.interfacies.DateFormatter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventRequestPostBuilder implements RequestPostBuilder<Event, EventPostRequest> {
    private DateFormatter dateParser;

    @Override
    public Event build(EventPostRequest request, User user) {
        Event event = new Event();

        event.setName(request.getName());

        String comment = request.getComment();
        if(!comment.isEmpty())
            event.setComment(comment);

        String playedString = request.getPlayed();
        event.setPlayed(dateParser.parseDateTime(playedString));
        event.setUser(user);

        String status = request.getStatus();
        if(status != null)
            event.setStatus(Event.EventStatus.valueOf(status));

        return event;
    }
}
