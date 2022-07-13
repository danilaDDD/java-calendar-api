package com.calendar.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class GenerateJWTTokenException extends AbstractRequestException {
    public GenerateJWTTokenException() {
        super("Запррос на генерацию токена не прошел авторизацию", HttpStatus.UNAUTHORIZED);
    }
}
