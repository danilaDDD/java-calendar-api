package com.calendar.responses;

import com.calendar.models.User;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter @Getter
public class UserResponse {
    private Long id;
    private String login, fio, email, sex;
    private int age;
    private boolean status;

    public UserResponse(User user){
            this.setLogin(user.getLogin())
                .setFio(user.getFio())
                .setEmail(user.getEmail())
                .setAge(user.getAge())
                .setStatus(user.isStatus())
                .setSex(user.getSex().toString())
                .setId(user.getId());
    }
}
