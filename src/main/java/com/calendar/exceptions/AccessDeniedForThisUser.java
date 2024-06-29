package com.calendar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedForThisUser extends UnauthorizedRequestException{
    public AccessDeniedForThisUser() {
        super("Доступ к данной операции для текущего пользователя запрещен");
    }
}
