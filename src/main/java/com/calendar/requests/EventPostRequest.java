package com.calendar.requests;

import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.services.EventGroupService;
import com.calendar.services.UserService;
import com.calendar.components.DateFormatter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
