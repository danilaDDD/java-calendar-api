package com.calendar.components;

public interface RequestPutBuilder<Entity, Request> {
    Entity build(Long id, Request request);
}
