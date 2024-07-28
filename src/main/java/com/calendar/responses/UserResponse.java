package com.calendar.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter @Getter
public class UserResponse extends SuccessEntityResponse{
    private Long id;
    private String login, fio, email, sex;
    private int age;
    private boolean status;
}
