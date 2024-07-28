package com.calendar.responses;

import java.util.List;

public class EventsResponse extends SuccessEntitiesResponse<EventResponse>{
    public EventsResponse(List<EventResponse> items) {
        super(items);
    }
}
