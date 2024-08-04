package com.calendar.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public interface DateFormatter {
    LocalDateTime parseDateTime(String data) throws DateTimeParseException;

    LocalDate parseDate(String data) throws DateTimeParseException;

    String formatDateTime(LocalDateTime dateTime);

    String formatDate(LocalDate date);
}
