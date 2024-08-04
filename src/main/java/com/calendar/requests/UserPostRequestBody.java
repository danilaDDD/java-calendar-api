package com.calendar.requests;

import com.calendar.models.User;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserPostRequestBody implements UserRequestBody {
    @Size(min = 3)
    @NotNull
    String login;
    @NotNull
    String password;

    @NotNull
    @Size(min = 5, max = 100)
    private String fio;

    @Nullable
    @Email
    private String email;

    @Nullable
    @Range(min = 5, max = 100)
    private int age;

    private boolean status = true;

    @Nullable
    @Enumerated(EnumType.STRING)
    private User.Sex sex;

    @Nullable
    private User.Role role;
}
