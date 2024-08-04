package com.calendar.requests;

import com.calendar.models.Event;

import java.time.LocalDateTime;

public interface EventRequestBody extends RequestBody{
    String getName();

    LocalDateTime getPlayed();

    String getComment();

    Event.EventStatus getStatus();
}
