package com.calendar.requests;

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
    private String status;
    @Nullable
    private String played;
}
