package com.calendar.requests;

import com.calendar.models.User;
import org.springframework.lang.Nullable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public interface UserRequestBody extends RequestBody {
    String getLogin();

    String getPassword();

    String getFio();

    String getEmail();

    boolean isStatus();

    User.Sex getSex();

    User.Role getRole();

    int getAge();
}
