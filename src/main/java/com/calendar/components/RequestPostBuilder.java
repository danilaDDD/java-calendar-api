package com.calendar.components;

public interface RequestPostBuilder<Entity, Request> {
    Entity build(Request request);
}
