package com.calendar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BadRequestException extends AbstractRequestException{

    public BadRequestException() {
        super("Bad Request", HttpStatus.BAD_REQUEST);
    }
}
