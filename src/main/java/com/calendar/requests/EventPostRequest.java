package com.calendar.requests;

import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Setter
@Getter
public class EventPostRequest {
    @NotNull
    String name;
    @Nullable
    String comment = null;
    @NotNull
    String played;
    @Nullable
    Event.EventStatus status;
}
