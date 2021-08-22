package com.calendar.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public interface DateParser {
    LocalDateTime parseDateTime(String data) throws DateTimeParseException;

    LocalDate parseDate(String data) throws DateTimeParseException;
}
