package com.calendar.controllers;

import com.calendar.exceptions.*;
import com.calendar.responses.DoneResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ GenerateJWTTokenException.class,
            NotFoundException.class,
            UnauthorizedRequestException.class,
            BadRequestException.class})
    public ResponseEntity<DoneResponse> handleAccessDeniedException(AbstractRequestException ex) {

        HttpStatus httpStatus = ex.getHttpStatus();

        DoneResponse doneResponse = new DoneResponse()
                .setHttpStatus(httpStatus)
                .addError(ex.getMessage());

        return new ResponseEntity<>(doneResponse, httpStatus);
    }
}
