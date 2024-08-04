package com.calendar.components;

import com.calendar.exceptions.BadRequestException;
import com.calendar.models.Event;
import com.calendar.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class EventsGetResponseFilter {
    private EventService eventService;
    private final DateFormatter dateFormatter;

    public List<Event> filter(Event.EventStatus status,
                              String fromDateString, String toDateString,
                              Long userId) {
        try {
            List<Event> events = eventService.findByUserId(userId);

            Stream<Event> eventsStream = events.stream();

            if (status != null) {
                eventsStream = eventsStream.filter(event -> event.getStatus().equals(status));
            }

            if (fromDateString != null) {
                LocalDateTime fromDate = dateFormatter.parseDateTime(fromDateString);
                eventsStream = eventsStream.filter(event -> event.getPlayed().isAfter(fromDate));
            }

            if (toDateString != null) {
                LocalDateTime toDate = dateFormatter.parseDateTime(toDateString);
                eventsStream = eventsStream.filter(event -> event.getPlayed().isBefore(toDate));
            }


            return eventsStream
                    .sorted(Comparator.comparing(Event::getPlayed))
                    .collect(Collectors.toList());

        }catch (DateTimeParseException e){
            throw new BadRequestException("date time format of get params is wrong format");
        }
    }
}
