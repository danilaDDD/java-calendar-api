package com.calendar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NotFoundException extends AbstractRequestException{
    public NotFoundException(){
        super("Ресурс не найден", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
