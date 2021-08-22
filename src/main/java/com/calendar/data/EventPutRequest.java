package com.calendar.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventPutRequest {
    private String name = null;
    private String comment = null;
    private String status = null;
    private String played = null;
}
