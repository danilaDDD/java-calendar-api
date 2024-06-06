package com.calendar.exceptions;

import org.springframework.http.HttpStatus;


public class BadRequestException extends AbstractRequestException{

    public BadRequestException() {
        super("Bad Request", HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
