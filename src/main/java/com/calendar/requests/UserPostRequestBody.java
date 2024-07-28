package com.calendar.requests;

import com.calendar.models.User;
import lombok.Data;
import org.springframework.lang.Nullable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class UserPostRequestBody implements UserRequestBody {
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
    private User.Sex sex;

    @Nullable
    private User.Role role;
}
