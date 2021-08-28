package com.calendar.interfacies;

public interface RequestPostBuilder<Entity, Request> {
    Entity build(Request request);
}
