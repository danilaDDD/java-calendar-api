package com.calendar.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AbstractRequestException  extends RuntimeException{
    private HttpStatus httpStatus;

    public AbstractRequestException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
