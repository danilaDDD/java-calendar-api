package com.calendar.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EventPutRequest {
    @Nullable
    private String name;
    @Nullable
    private String comment;
    @Nullable
    private String status;
    @Nullable
    private String played;
}
