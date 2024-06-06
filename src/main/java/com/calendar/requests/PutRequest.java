package com.calendar.requests;

public interface PutRequest<T> {
    T update(T source);
}
