package com.calendar.data;

import com.calendar.models.EventGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventGroupResponce {
    Long id;
    String name;

    public EventGroupResponce(EventGroup group){
        setId(group.getId());
        setName(group.getName());
    }
}
