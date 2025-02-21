package com.calendar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedRequestException extends AbstractRequestException{

    public UnauthorizedRequestException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
