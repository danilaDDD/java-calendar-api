package com.calendar.requests;

import com.calendar.constants.Constants;
import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventPutRequestBody implements EventRequestBody {
    @Nullable
    @Size(min = 3)
    private String name;
    @Nullable
    private String comment;
    @Nullable
    private Event.EventStatus status;
    @Nullable
    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime played;
}
