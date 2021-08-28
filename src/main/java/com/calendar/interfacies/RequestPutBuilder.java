package com.calendar.interfacies;

public interface RequestPutBuilder<Entity, Request> {
    Entity build(Long id, Request request);
}
