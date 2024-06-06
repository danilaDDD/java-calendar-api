package com.calendar.services;

import com.calendar.models.AuthEntity;

import java.util.Optional;

public interface AuthService<T extends AuthEntity> {
    Optional<T> findByLoginAndEncodedPassword(String login, String passwordHash);

    Optional<T> findByLoginAndPassword(String login, String password);
}
