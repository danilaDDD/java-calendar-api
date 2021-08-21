package com.calendar.data;

import com.calendar.models.User;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter @Getter
public class UserResponse {
    private Integer id;
    private String login, fio, email;
    private int age;
    private boolean status;

    public UserResponse(User user){
            this.setLogin(user.getLogin())
                .setFio(user.getFio())
                .setEmail(user.getEmail())
                .setAge(user.getAge())
                .setStatus(user.isStatus())
                .setId(user.getId());
    }
}
