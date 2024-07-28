package com.calendar.requests;

import com.calendar.models.Event;

public interface EventRequestBody extends RequestBody{
    String getName();

    String getPlayed();

    String getComment();

    Event.EventStatus getStatus();
}
