package com.calendar.models;

public interface AuthEntity {
    String getLogin();
    void setLogin(String login);
    String getEncodedPassword();
    void setEncodedPassword(String encodedPassword);
}
