package com.calendar.data;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
public class DoneResponse {
    public static DoneResponse getSuccessInstance(){
        return new DoneResponse();
    }

    public static DoneResponse getFromExceptionInstance(Exception e, HttpStatus httpStatus){
        DoneResponse done = new DoneResponse()
                .setHttpStatus(httpStatus);

        done.addError(e.getMessage());
        return done;
    }

    private boolean success = true;

    private HttpStatus httpStatus = HttpStatus.OK;

    private int httpStatusCode;
    private Set<String> errors = new HashSet<>();

    public DoneResponse addError(String error){
        this.setSuccess(false);

        errors.add(error);

        return this;
    }

    public DoneResponse setHttpStatus(HttpStatus status){
        this.httpStatus = status;

        this.setHttpStatusCode(status.value());

        return this;
    }
}
