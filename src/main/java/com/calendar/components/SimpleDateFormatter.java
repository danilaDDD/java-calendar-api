package com.calendar.components;

import com.calendar.interfacies.DateFormatter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Component
public class SimpleDateFormatter implements DateFormatter {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public LocalDateTime parseDateTime(String data) throws DateTimeParseException {
        return LocalDateTime.parse(data, dateTimeFormatter);
    }

    @Override
    public LocalDate parseDate(String data) throws DateTimeParseException {
        return LocalDate.parse(data, dateFormatter);
    }

    @Override
    public String printDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    @Override
    public String printDate(LocalDate date) {
        return date.format(dateFormatter);
    }
}
