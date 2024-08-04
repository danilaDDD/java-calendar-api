package com.calendar.requests;

import com.calendar.constants.Constants;
import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
public class EventPostRequestBody implements EventRequestBody {
    @Size(min = 3)
    @NotNull
    private String name;
    @Nullable
    private String comment = null;
    @NotNull
    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime played;
    @Nullable
    private Event.EventStatus status;
}
