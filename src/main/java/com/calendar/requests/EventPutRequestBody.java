package com.calendar.requests;

import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class EventPutRequestBody implements EventRequestBody {
    @Nullable
    private String name;
    @Nullable
    private String comment;
    @Nullable
    private Event.EventStatus status;
    @Nullable
    private String played;
}
