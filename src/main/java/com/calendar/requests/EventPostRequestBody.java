package com.calendar.requests;

import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class EventPostRequestBody implements EventRequestBody {
    @NotNull
    String name;
    @Nullable
    String comment = null;
    @NotNull
    String played;
    @Nullable
    Event.EventStatus status;
}
