package com.calendar.requests;

import com.calendar.models.User;

public interface AuthByUserPostRequest<T> {
    T create(User user);
}
