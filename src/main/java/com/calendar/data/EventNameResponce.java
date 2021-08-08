package com.calendar.data;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventNameResponce {
    Long id;
    String name;
}
