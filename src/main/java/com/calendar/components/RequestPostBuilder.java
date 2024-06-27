package com.calendar.components;

import com.calendar.models.User;

public interface RequestPostBuilder<Entity, Request> {
    Entity build(Request request, User user);
}
