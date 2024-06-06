package com.calendar.requests;

import com.calendar.models.User;
import lombok.Data;
import org.springframework.lang.Nullable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class UserPostRequest {
    @NotNull
    String login;
    @NotNull
    String password;

    @NotNull
    private String fio;

    @Nullable
    private String email;

    @Nullable
    private int age;

    private boolean status = true;

    @Nullable
    @Enumerated(EnumType.STRING)
    private User.Sex sex = null;

    @Nullable
    private User.Role role = null;

    public User.Role getRoleOrDefault(){
        return role == null ? User.Role.USER: role;
    }

    public User.Sex getSexOrDefault(){
        return sex == null ? User.Sex.UNKNOWN: sex;
    }
}
