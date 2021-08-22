package com.calendar.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class SimpleDateParser implements DateParser{
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
}
