package com.calendar.requests;

import com.calendar.models.User;

public interface PostRequest<Entity> {
    Entity create(User user);
}
