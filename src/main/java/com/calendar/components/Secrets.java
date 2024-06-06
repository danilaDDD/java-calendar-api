package com.calendar.components;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Secrets {
    @Getter
    @Value("${jwt.secret}")
    private String apiClientSecret;

    @Getter
    @Value("${jwt.user_secret}")
    private String userSecret;

    @Value("${expiration.days}")
    private int expirationDays;

    public int getApiClientActualDays(){
        return expirationDays;
    }

    public int getUserActualDays(){
        return expirationDays;
    }
}
