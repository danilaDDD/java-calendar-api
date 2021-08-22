package com.calendar.requests;

public interface PutRequest<Entity> {
    Entity update(Entity source);
}
