package com.calendar.responses;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class DoneResponse {

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
