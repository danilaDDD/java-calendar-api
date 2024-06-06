package com.calendar.controllers;

import com.calendar.responses.DoneResponse;
import com.calendar.exceptions.AbstractRequestException;
import com.calendar.exceptions.BadRequestException;
import com.calendar.exceptions.GenerateJWTTokenException;
import com.calendar.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler({ GenerateJWTTokenException.class, NotFoundException.class, BadRequestException.class})
    public ResponseEntity<DoneResponse> handleAccessDeniedException(
            AbstractRequestException ex) {

        HttpStatus httpStatus = ex.getHttpStatus();

        DoneResponse doneResponse = new DoneResponse()
                .setHttpStatus(httpStatus)
                .addError(ex.getMessage());

        return new ResponseEntity<>(doneResponse, httpStatus);
    }
}
