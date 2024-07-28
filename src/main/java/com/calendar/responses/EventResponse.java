package com.calendar.responses;

import com.calendar.models.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

//@FIXME убрать поле formatter
@Accessors(chain = true)
@Getter
@Setter
public class EventResponse extends SuccessEntityResponse {

    long id;
    String name;
    String comment;
    Event.EventStatus status;
    String played;
    String user;
}
