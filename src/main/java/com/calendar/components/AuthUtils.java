package com.calendar.components;

import com.calendar.exceptions.UnauthorizedRequestException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthUtils {
    public long getUserId(HttpServletRequest request){
        Object value = request.getAttribute("userId");
        if(value == null)
            throw new UnauthorizedRequestException("auth by user with error");

        return (long) value;
    }
}
