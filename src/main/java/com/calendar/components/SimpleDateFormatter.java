package com.calendar.components;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.calendar.constants.Constants;

@Component
public class SimpleDateFormatter implements DateFormatter {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    @Override
    public LocalDateTime parseDateTime(String data) {
        return LocalDateTime.parse(data, dateTimeFormatter);
    }

    @Override
    public LocalDate parseDate(String data) {
        return LocalDate.parse(data, dateFormatter);
    }

    @Override
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

    @Override
    public String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }
}
