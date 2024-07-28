package com.calendar.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthRequestBody implements RequestBody{
    @NotNull
    private String login;
    @NotNull
    private String password;
}
